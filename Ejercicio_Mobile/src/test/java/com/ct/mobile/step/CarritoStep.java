package com.ct.mobile.step;

import com.ct.mobile.config.MobileDriverManager;
import com.ct.mobile.view.CarritoView;
import org.junit.jupiter.api.Assertions;

/**
 * Clase que gestiona las acciones y validaciones del carrito de compras.
 * Representa la “lógica” de negocio dentro del flujo del test.
 */
public class CarritoStep {

    private int totalEsperado = 0;  // Acumula el total de productos añadidos por el usuario

    /**
     * Acumula las unidades que el usuario ha agregado al carrito.
     * @param unidades cantidad de productos agregados
     */
    public void acumular(int unidades) {
        totalEsperado += unidades;
    }

    /**
     * Espera a que la vista del carrito sea visible antes de continuar.
     * Útil para evidencias o sincronización con la UI.
     */
    public void mostrarVistaCarrito() {
        CarritoView view = new CarritoView(MobileDriverManager.getDriver());
        view.esperarCarritoVisible();
        System.out.println("🛍️ Vista del carrito visible y lista para validaciones.");
    }

    /**
     * Valida que el contador del carrito coincida con el total de productos agregados.
     * Si el contador no coincide, falla el test con un mensaje claro.
     */
    public void validarContadorCarrito() throws InterruptedException {
        CarritoView view = new CarritoView(MobileDriverManager.getDriver());

        // Espera breve para asegurar que la UI refleje el cambio visual (badge actualizado)
        Thread.sleep(800);

        boolean ok = view.esperarBadgeIgualA(totalEsperado);  // compara badge actual con el total esperado
        int actual = view.obtenerContadorCarrito();

        Assertions.assertTrue(ok, "❌ El contador del carrito no coincide con lo esperado. Esperado: " 
                + totalEsperado + ", Actual: " + actual);
        System.out.println("✅ Contador verificado: el carrito refleja " + actual + " producto(s) correctamente.");
    }

    /**
     * Alias para compatibilidad con el Glue (CarritoStepDef).
     * Permite mantener el nombre assertCartUpdated() en el código de pasos.
     */
    public void assertCartUpdated() throws InterruptedException {
        validarContadorCarrito();
    }
}

