package com.ct.mobile.step;

import com.ct.mobile.config.MobileDriverManager;
import com.ct.mobile.view.CarritoView;
import org.assertj.core.api.Assertions;

/**
 * Lógica de pasos relacionados con el carrito de compras.
 * Coordina las validaciones entre la vista (CarritoView) y los valores esperados.
 */
public class CarritoStep {

    private int totalEsperado;

    /** Suma al total esperado (lo que el usuario agregó). */
    public void acumular(int unidades) {
        totalEsperado += unidades;
    }

    /** Espera a que el carrito sea visible antes de continuar (para evidencias). */
    public void mostrarVistaCarrito() {
        new CarritoView(MobileDriverManager.getDriver()).esperarCarritoVisible();
        System.out.println("🛒 Vista del carrito lista para captura.");
    }

    /**
     * Compara el contador del carrito (badge) con el total acumulado esperado.
     * Lanza error si el número mostrado no coincide con los productos agregados.
     */
    public void validarContadorCarrito() {
        CarritoView view = new CarritoView(MobileDriverManager.getDriver());
        boolean ok = view.esperarBadgeIgualA(totalEsperado);
        int actual = view.obtenerContador();

        Assertions.assertThat(ok)
                .as("El badge del carrito debería mostrar %s pero muestra %s", totalEsperado, actual)
                .isTrue();

        System.out.println("El carrito refleja correctamente " + actual + " productos añadidos.");
    }
}
