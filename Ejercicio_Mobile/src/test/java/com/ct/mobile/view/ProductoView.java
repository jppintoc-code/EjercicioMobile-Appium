package com.ct.mobile.view;

import com.ct.mobile.config.MobileDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductoView {

    private final AppiumDriver driver;

    // Título en el detalle del producto (ajusta si tu resource-id difiere)
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement productTitle;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/plusIV")
    private WebElement btnMas;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElement btnAgregarCarrito;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
    private WebElement imgCarrito;

    public ProductoView() {
        this(MobileDriverManager.getDriver());
    }

    public ProductoView(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalStateException("El AppiumDriver es null. Verifica que esté inicializado antes de usar ProductoView.");
        }
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /** Espera a que el detalle esté visible (título del producto) */
    public boolean showProductDetail() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOf(productTitle));
            return productTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getBtnMas() { return btnMas; }
    public WebElement getBtnAgregarCarrito() { return btnAgregarCarrito; }
    public WebElement getImgCarrito() { return imgCarrito; }

    /** Localizador dinámico por nombre de producto en la grilla/lista */
    public static By seleccionarProducto(String nombre) {
        // Puedes ajustar a tu estrategia preferida (id/text/xpath accesible)
        // Aquí un ejemplo simple por texto visible:
        return By.xpath("//android.widget.TextView[@text='" + nombre + "']");
    }
}
