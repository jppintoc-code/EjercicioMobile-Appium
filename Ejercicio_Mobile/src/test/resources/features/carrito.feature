@test
Feature: Validar la funcionalidad del carrito de compras
  Como usuario de la tienda
  Quiero agregar productos al carrito
  Para verificar que el contador se actualiza correctamente

  Scenario Outline: Carrito se actualiza al agregar productos
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galeria
    When agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
    Then valido el carrito de compra actualice correctamente

    Examples:
      | PRODUCTO                 | UNIDADES |
      | Sauce Labs Backpack      | 1        |
      | Sauce Labs Bolt T-Shirt  | 1        |
      | Sauce Labs Bike Light    | 2        |
