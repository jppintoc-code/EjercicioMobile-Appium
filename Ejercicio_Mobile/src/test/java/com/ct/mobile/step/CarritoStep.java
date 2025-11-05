package com.ct.mobile.step;

import com.ct.mobile.config.MobileDriverManager;
import com.ct.mobile.view.CarritoView;
import org.junit.jupiter.api.Assertions;

/**
 * Acciones y validaciones del carrito.
 * Mantiene un contador esperado y lo compara con el badge de la app.
 */
public class CarritoStep {

    private int totalEsperado = 0;

    /** Suma unidades al total esperado (lo que el usuario agregó). */
    public void acumular(int unidades) {
        totalEsperado += unidades;
    }

    /** Espera que la vista del carrito sea visible (icono/badge) para evidencias. */
    public void mostrarVistaCarrito() {
        CarritoView view = new CarritoView(MobileDriverManager.getDriver());
        view.esperarCarritoVisible();
        System.out.println("🛒 Carrito visible en pantalla.");
    }

    /** Valida que el badge del carrito refleje el total esperado. */
    public void validarContadorCarrito() throws InterruptedException {
        CarritoView view = new CarritoView(MobileDriverManager.getDriver());

        // Pequeño colchón para que la UI actualice el badge
        Thread.sleep(800);

        boolean ok = view.esperarBadgeIgualA(totalEsperado);
        int actual = view.obtenerContadorCarrito();

        Assertions.assertTrue(
                ok,
                "❌ El contador del carrito no coincide. Esperado: " + totalEsperado + " | Actual: " + actual
        );
        System.out.println("✅ Contador verificado: " + actual + " artículo(s).");
    }

    /** Alias que usa el Glue. */
    public void assertCartUpdated() throws InterruptedException {
        validarContadorCarrito();
    }
}
