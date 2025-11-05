package com.ct.mobile.view;

import com.ct.mobile.config.MobileDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Elementos y utilidades de la vista del carrito (icono y badge).
 * NOTA: Los IDs son los de Sauce Labs MyDemoApp:
 *   - cartIV  : icono del carrito
 *   - cartTV  : badge (contador)
 */
public class CarritoView {

    private final AppiumDriver driver;

    // Icono del carrito (visible en toolbar)
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
    private WebElement iconoCarrito;

    // Badge/contador del carrito
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartTV")
    private WebElement badgeCarrito;

    public CarritoView() {
        this(MobileDriverManager.getDriver());
    }

    public CarritoView(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalStateException(
                    "El AppiumDriver es null. Verifica que esté inicializado antes de usar CarritoView.");
        }
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /** Espera a que el icono del carrito esté visible. */
    public void esperarCarritoVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(iconoCarrito));
    }

    /**
     * Espera a que el badge muestre exactamente el valor esperado.
     * Si esperas 0 y el badge no está visible, se considera correcto.
     */
    public boolean esperarBadgeIgualA(int totalEsperado) {
        try {
            // Si esperamos 0 y el badge no se muestra, es válido (la app oculta el badge cuando está en 0)
            if (totalEsperado == 0) {
                // Intento breve de visibilidad; si no aparece, devolvemos true
                try {
                    new WebDriverWait(driver, Duration.ofSeconds(2))
                            .until(ExpectedConditions.visibilityOf(badgeCarrito));
                    // Si se ve, entonces debe decir "0"
                    return "0".equals(textoSeguro(badgeCarrito));
                } catch (Exception ignore) {
                    return true; // no se ve → se asume 0 correcto
                }
            }

            // Para >0 esperamos que el badge sea visible y su texto coincida
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(badgeCarrito));

            return String.valueOf(totalEsperado).equals(textoSeguro(badgeCarrito));
        } catch (Exception e) {
            return false;
        }
    }

    /** Devuelve el número del badge. Si no hay badge visible, retorna 0. */
    public int obtenerContadorCarrito() {
        try {
            if (badgeCarrito != null && badgeCarrito.isDisplayed()) {
                String raw = textoSeguro(badgeCarrito);
                return Integer.parseInt(raw.trim());
            }
        } catch (Exception ignore) { }
        return 0;
    }

    // ---------- Helpers ----------

    private String textoSeguro(WebElement el) {
        try {
            return el.getText() == null ? "" : el.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
