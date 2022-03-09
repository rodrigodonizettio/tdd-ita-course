package br.com.rodrigodonizettio.week3.store;

import br.com.rodrigodonizettio.week3.store.observer.ManualMockShoppingCartAmountCounterObserver;
import br.com.rodrigodonizettio.week3.store.observer.ManualMockShoppingCartSumPriceObserver;
import br.com.rodrigodonizettio.week3.store.observer.ShoppingCartCounterObserver;
import br.com.rodrigodonizettio.week3.store.observer.ShoppingCartPriceObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {
    @Mock
    ShoppingCart shoppingCart = new ShoppingCart();


    @BeforeEach
    void beforeEachTest() {
        shoppingCart.emptyCart();
    }

    @Test
    void shouldCalculateTotalPriceTest() {
        Product shoes = new Product("shoes", 300);
        Product pants = new Product("pants", 80);
        Product tShirt = new Product("tShirt", 40);
        shoppingCart.addProduct(shoes);
        shoppingCart.addProduct(pants);
        shoppingCart.addProduct(tShirt);

        assertEquals(420, shoppingCart.calculateTotalPrice());
    }

    @Test
    void shouldCountCurrentProductsAmountTest() {
        ShoppingCartCounterObserver manualMockShoppingCartAmountCounterObserver = new ManualMockShoppingCartAmountCounterObserver();
        shoppingCart.addObserver(ManualMockShoppingCartAmountCounterObserver.OBSERVER_TYPE, manualMockShoppingCartAmountCounterObserver);

        Product shoes = new Product("shoes", 300);
        Product pants = new Product("pants", 80);
        Product tShirt = new Product("tShirt", 40);
        shoppingCart.addProduct(shoes);
        shoppingCart.addProduct(pants);

        assertEquals(2, manualMockShoppingCartAmountCounterObserver.getCurrentProductsAmount());

        shoppingCart.addProduct(tShirt);

        assertEquals(3, manualMockShoppingCartAmountCounterObserver.getCurrentProductsAmount());
    }

    @Test
    void shouldSumCurrentProductsPriceTest() {
        ShoppingCartPriceObserver manualMockShoppingCartCurrentPriceObserver = new ManualMockShoppingCartSumPriceObserver();
        shoppingCart.addObserver(ManualMockShoppingCartSumPriceObserver.OBSERVER_TYPE, manualMockShoppingCartCurrentPriceObserver);

        Product shoes = new Product("shoes", 300);
        Product pants = new Product("pants", 80);
        Product tShirt = new Product("tShirt", 40);
        shoppingCart.addProduct(shoes);
        shoppingCart.addProduct(pants);

        assertEquals(380, manualMockShoppingCartCurrentPriceObserver.getSumCurrentProductsPrice());

        shoppingCart.addProduct(tShirt);

        assertEquals(420, manualMockShoppingCartCurrentPriceObserver.getSumCurrentProductsPrice());
    }
}
