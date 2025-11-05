package com.ct.mobile.config;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;

public class DesiredCapsFactory {

    // Android
    public static Capabilities forAndroid(Config cfg) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(cfg.get("platformName"));
        options.setAutomationName(cfg.get("automationName"));
        options.setDeviceName(cfg.get("deviceName"));
        options.setUdid(cfg.get("udid"));

        // Si hay ruta del APK, la usa; si no, tomará el package/activity ya instalado
        if (cfg.get("app") != null) options.setApp(cfg.get("app"));
        if (cfg.get("appPackage") != null) options.setAppPackage(cfg.get("appPackage"));
        if (cfg.get("appActivity") != null) options.setAppActivity(cfg.get("appActivity"));
        if (cfg.get("appWaitActivity") != null) options.setAppWaitActivity(cfg.get("appWaitActivity"));

        if (cfg.get("autoGrantPermissions") != null)
            options.setCapability("autoGrantPermissions", Boolean.parseBoolean(cfg.get("autoGrantPermissions")));

        return options;
    }

    // iOS (por compatibilidad futura)
    public static Capabilities forIOS(Config cfg) {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName(cfg.get("platformName"));
        options.setAutomationName(cfg.get("automationName"));
        if (cfg.get("app") != null) options.setApp(cfg.get("app"));
        options.setDeviceName(cfg.get("deviceName"));
        options.setUdid(cfg.get("udid"));
        return options;
    }
}
