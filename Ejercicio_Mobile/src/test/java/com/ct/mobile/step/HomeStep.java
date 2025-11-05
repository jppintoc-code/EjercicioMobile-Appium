package com.ct.mobile.step;

import com.ct.mobile.config.MobileDriverManager;
import com.ct.mobile.view.HomeView;
import io.appium.java_client.AppiumDriver;

/**
 * Define las acciones de la pantalla principal (Home) en la capa de Steps.
 *  - Driver validado en el constructor.
 *  - Metodo público que ejecuta la validación de la vista principal.
 */
public class HomeStep {

    /** Driver de Appium compartido. */
    private final AppiumDriver driver;

    /**
     * Constructor: obtiene el driver actual y lanza excepción si no está inicializado.
     * Esto evita errores silenciosos en la ejecución de las pruebas.
     */
    public HomeStep() {
        this.driver = MobileDriverManager.getDriver();
        if (this.driver == null) {
            throw new IllegalStateException(
                    "El driver no ha sido inicializado. " +
                            "Por favor, asegúrate de iniciar Appium y el emulador " +
                            "antes de crear una instancia de HomeStep."
            );
        }
    }

    /**
     * Verifica que la vista principal (Home) esté visible en la app.
     * Si no aparece en el tiempo de espera, lanza un AssertionError para marcar el test como fallido.
     */
    public void showMainView() {
        HomeView homeView = new HomeView(driver);
        boolean visible = homeView.showMainView();

        if (!visible) {
            throw new AssertionError("⚠️ No se mostró la galería de productos dentro del tiempo esperado.");
        } else {
            System.out.println("✅ Galería de productos cargada correctamente en la vista principal.");
        }
    }
}
