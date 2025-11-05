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
 * Representa la vista del carrito de compras dentro de la app.
 * Contiene los elementos visuales y acciones para validar su estado.
 */
public class CarritoView {

    private final AppiumDriver driver;

    // Elementos principales del carrito
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/titleTV")
    private WebElement nombreProductoCarrito;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
    private WebElement badgeCarrito;

    /** Constructor principal: inicializa la vista del carrito. */
    public CarritoView(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalStateException("El AppiumDriver es null. Verifica que esté inicializado antes de usar CarritoView.");
        }
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /** Espera a que la vista del carrito esté visible. */
    public void esperarCarritoVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOf(nombreProductoCarrito));
        } catch (Exception e) {
            throw new RuntimeException("No se pudo visualizar el carrito en el tiempo esperado.", e);
        }
    }

    /** Espera que el badge del carrito muestre el total esperado. */
    public boolean esperarBadgeIgualA(int esperado) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> obtenerContador() == esperado);
        } catch (Exception e) {
            System.out.println("El contador del carrito no coincidió con lo esperado dentro del tiempo.");
            return false;
        }
    }

    /** Obtiene el valor numérico mostrado en el badge del carrito. */
    public int obtenerContador() {
        try {
            String texto = badgeCarrito.getText().trim();
            return texto.isEmpty() ? 0 : Integer.parseInt(texto);
        } catch (Exception e) {
            System.out.println("No se pudo leer el contador del carrito.");
            return -1;
        }
    }
}
