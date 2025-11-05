package com.ct.mobile.step;

import com.ct.mobile.config.MobileDriverManager;
import com.ct.mobile.view.CarritoView;
import org.assertj.core.api.Assertions;

/**
 * Mantiene un conteo simple de lo que agregamos para contrastarlo con el badge.
 * No usa sleep; confía en esperas explícitas de la View.
 */
public class CarritoStep {

    private int totalEsperado;

    /** Suma al total esperado (lo que el usuario agregó). */
    public void acumular(int unidades) {
        totalEsperado += unidades;
    }

    /** Espera una vista válida del carrito (o el badge) para evidencias. */
    public void mostrarVistaCarrito() {
        new CarritoView(MobileDriverManager.getDriver()).esperarCarritoVisible();
        System.out.println("Vista del carrito lista para captura.");
    }

    /** Compara el badge del carrito contra el total acumulado. */
    public void validarContadorCarrito() {
        CarritoView view = new CarritoView(MobileDriverManager.getDriver());
        boolean ok = view.esperarBadgeIgualA(totalEsperado);
        int actual = view.obtenerContadorBadge();

        Assertions.assertThat(ok && actual == totalEsperado)
                .as("El contador no coincide. Esperado: " + totalEsperado + " | Actual: " + actual)
                .isTrue();

        System.out.println("Contador correcto: " + actual + " artículo(s).");
    }
}
