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
 * Representa la vista del carrito dentro de la app de SauceLabs.
 * Aquí se declaran los elementos y acciones que se pueden realizar
 * en la pantalla del carrito (por ejemplo: validar productos, totales, etc.)
 */
public class CarritoView {

    private final AppiumDriver driver;

    // 🛒 Identificador visual del carrito (por ejemplo, el título o nombre del producto en el carrito)
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/titleTV")
    private WebElement nombreProductoCarrito;

    // 🔢 Contador que muestra cuántos productos hay en el carrito (opcional)
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
    private WebElement badgeCarrito;

    public CarritoView() {
        this(MobileDriverManager.getDriver());
    }

    public CarritoView(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalStateException("El AppiumDriver es null. Verifica que esté inicializado antes de usar CarritoView.");
        }
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /**
     * ✅ Valida que la vista del carrito esté visible en pantalla.
     * @return true si se muestra correctamente el carrito, false si no.
     */
    public boolean mostrarVistaCarrito() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.visibilityOf(nombreProductoCarrito));
            return nombreProductoCarrito.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 🧭 Espera a que el ícono del carrito sea visible.
     * Esto es útil cuando el flujo regresa al carrito desde otra vista.
     */
    public void esperarCarritoVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(badgeCarrito));
    }

    /**
     * 🧩 Devuelve el contador o ícono del carrito para validaciones más detalladas.
     */
    public WebElement getBadgeCarrito() {
        return badgeCarrito;
    }
}
