package com.ct.mobile.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverManager {
    public static AppiumDriver driver;

    /** Retorna el driver activo */
    public static AppiumDriver getDriver() {
        return driver;
    }

    /**
     * Inicia el driver para Android o iOS según el parámetro 'platform'.
     * Incluye una pequeña validación para asegurar que el Appium Server esté bien configurado.
     */
    public static void startDriver(String platform, String serverUrl) {
        if (driver != null) return;

        Config cfg = new Config(platform);
        Capabilities caps = "ios".equalsIgnoreCase(platform)
                ? DesiredCapsFactory.forIOS(cfg)
                : DesiredCapsFactory.forAndroid(cfg);

        try {
            // Si no se pasa URL, usa la del properties o la predeterminada
            String defaultUrl = cfg.get("appiumServerUrl");
            if (serverUrl == null || serverUrl.isEmpty()) {
                serverUrl = (defaultUrl != null && !defaultUrl.isBlank())
                        ? defaultUrl
                        : "http://127.0.0.1:4723/wd/hub"; //  se agrega /wd/hub por robustez
            }

            URL url = new URL(serverUrl);

            driver = "ios".equalsIgnoreCase(platform)
                    ? new IOSDriver(url, caps)
                    : new AndroidDriver(url, caps);

        } catch (MalformedURLException e) {
            throw new RuntimeException("❌ URL de Appium inválida: " + serverUrl, e);
        } catch (Exception e) {
            throw new RuntimeException(
                    "⚠️ No se pudo iniciar el driver. Verifica que Appium esté corriendo y el puerto 4723 libre.",
                    e
            );
        }
    }

    /** Cierra el driver si está activo, liberando el puerto. */
    public static void stopDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
