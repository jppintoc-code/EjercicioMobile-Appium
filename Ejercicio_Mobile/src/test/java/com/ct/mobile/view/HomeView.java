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
 * HomeView (vista principal de la app)
 * Representa la galería de productos y valida que la pantalla principal esté visible.
 */
public class HomeView {

    /** Driver de Appium inyectado (final para mantenerlo inmutable). */
    private final AppiumDriver driver;

    /** Elemento principal de la pantalla de productos (título o lista). */
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement mainScreen;

    /** Constructor por defecto: obtiene el driver del MobileDriverManager. */
    public HomeView() {
        this(MobileDriverManager.getDriver());
    }

    /** Constructor con validación: asegura que el driver esté inicializado. */
    public HomeView(AppiumDriver driver) {
        if (driver == null) {
            throw new IllegalStateException(
                    "El AppiumDriver es null. " +
                            "Verifica que esté inicializado antes de usar HomeView."
            );
        }
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    /**
     * Espera hasta que la galería principal (mainScreen) sea visible.
     * @return true si se muestra correctamente, false si expira el tiempo de espera.
     */
    public boolean showMainView() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.visibilityOf(mainScreen));
            System.out.println("🟢 Pantalla principal de productos visible.");
            return mainScreen.isDisplayed();
        } catch (Exception e) {
            System.out.println("No se pudo validar la pantalla principal: " + e.getMessage());
            return false;
        }
    }
}
