package br.com.rodrigodonizettio.week3.store;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductTest {
    Product product = new Product("shoes", 300);

    @Test
    void shouldRetrieveNameTest() { assertEquals("shoes", product.getName()); }

    @Test
    void shouldRetrievePriceTest() {
        assertEquals(300, product.getPrice());
    }
}
