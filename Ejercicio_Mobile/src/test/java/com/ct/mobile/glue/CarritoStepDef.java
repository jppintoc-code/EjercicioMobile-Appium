package com.ct.mobile.glue;

import com.ct.mobile.step.CarritoStep;
import com.ct.mobile.step.HomeStep;
import com.ct.mobile.step.ProductoStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CarritoStepDef {

    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicacionDeSauceLabs() {
        System.out.println("✅ App iniciada: conectamos con el emulador sin problemas.");
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGaleria() {
        new HomeStep().showMainView();
        System.out.println("🟢 La galería de productos se ve bien.");
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregoDelSiguienteProducto(int unidades, String producto) {
        final String plural = (unidades == 1) ? "unidad" : "unidades";

        ProductoStep step = new ProductoStep();
        step.seleccionarProducto(producto);   // toca el ítem en la galería
        step.showProductDetail();             // verifica que se cargue su detalle
        step.agregarProducto(producto, unidades); // presiona + las veces y agrega al carrito

        System.out.println("➕ Se añadieron " + unidades + " " + plural + " de \"" + producto + "\" al carrito.");
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void validoElCarritoDeCompraActualiceCorrectamente() throws InterruptedException {
        // Abre el carrito y valida que el contador/estado refleje lo agregado
        new ProductoStep().abrirCarrito();
        new CarritoStep().assertCartUpdated();

        System.out.println("📦✨ El carrito refleja correctamente los productos añadidos.");
    }
}
