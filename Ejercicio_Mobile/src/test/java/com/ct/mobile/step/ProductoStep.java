package com.ct.mobile.step;

import com.ct.mobile.config.MobileDriverManager;
import com.ct.mobile.view.ProductoView;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductoStep {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public ProductoStep() {
        this.driver = MobileDriverManager.getDriver();
        if (this.driver == null) {
            throw new IllegalStateException("El driver no ha sido inicializado. Inicia el driver antes de usar ProductoStep.");
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void seleccionarProducto(String nombreProducto){
        // Localizador dinámico desde la view
        By productImage = ProductoView.seleccionarProducto(nombreProducto);
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(productImage));
            element.click();
            System.out.println("🛍️ Producto seleccionado: " + nombreProducto);
        } catch (Exception e) {
            Assertions.fail("No se encontró el producto en la galería: " + nombreProducto);
        }
    }

    /** 👉 Método que faltaba y que el glue está invocando */
    public void showProductDetail(){
        ProductoView view = new ProductoView(driver);
        boolean visible = view.showProductDetail();
        if (!visible) {
            throw new AssertionError("❌ No se cargó el detalle del producto en el tiempo esperado.");
        }
        System.out.println("🔎 Detalle del producto visible correctamente.");
    }

    public void agregarProducto(String nombreProducto, int unidades) {
        ProductoView view = new ProductoView(driver);

        // Suma unidades (si piden 1, no se toca el '+')
        for (int i = 1; i < unidades; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(view.getBtnMas())).click();
        }

        // Agregar al carrito
        wait.until(ExpectedConditions.elementToBeClickable(view.getBtnAgregarCarrito())).click();
        System.out.println("🧺 Agregado al carrito: " + nombreProducto + " (" + unidades + ").");
    }

    public void abrirCarrito() {
        ProductoView view = new ProductoView(driver);
        wait.until(ExpectedConditions.elementToBeClickable(view.getImgCarrito())).click();
        System.out.println("🧭 Abrimos la vista de carrito para validar el estado.");
    }
}
