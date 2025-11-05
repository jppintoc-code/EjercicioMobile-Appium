package com.ct.mobile.hooks;

import com.ct.mobile.config.MobileDriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

    @Before
    public static void beforeAll() {
        // para inciar cada test
        String platform = System.getProperty("platformName", "Android");
        String serverUrl = System.getProperty("appiumServerUrl", "http://0.0.0.0:4723/wd/hub");
        if (MobileDriverManager.getDriver() == null) {
            MobileDriverManager.startDriver(platform, serverUrl);
        }
    }


    @After
    public void after() {
        MobileDriverManager.stopDriver();
    }

    @AfterStep
    public void takeScreenshot(Scenario scenario) {
        AppiumDriver driver = MobileDriverManager.getDriver();
        if (driver != null) {
            try {
                byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", "evidencia");
            } catch (Exception e) {
                System.out.println("No se pudo capturar screenshot: " + e.getMessage());
            }
        }
    }
}
