package br.com.rodrigodonizettio.week3.store;

import br.com.rodrigodonizettio.week3.store.observer.ManualMockShoppingCartAmountCounterObserver;
import br.com.rodrigodonizettio.week3.store.observer.ManualMockShoppingCartSumPriceObserver;
import br.com.rodrigodonizettio.week3.store.observer.ShoppingCartObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private List<Product> products = new ArrayList<>();
    private Map<String, ShoppingCartObserver> observers = new HashMap<>();

    public void addProduct(Product product) {
        products.add(product);
        notifyObservers(product);
    }

    public Integer calculateTotalPrice() {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void emptyCart() {
        products.clear();
    }

    public void addObserver(String observerType, ShoppingCartObserver observer) {
        observers.put(observerType, observer);
    }

    private void notifyObservers(Product product) {
        for(var entry : observers.entrySet()) {
            switch (entry.getKey()) {
                case ManualMockShoppingCartAmountCounterObserver.OBSERVER_TYPE:
                    ((ManualMockShoppingCartAmountCounterObserver) entry.getValue()).countCurrentProductsAmount();
                    break;
                case ManualMockShoppingCartSumPriceObserver.OBSERVER_TYPE:
                    ((ManualMockShoppingCartSumPriceObserver) entry.getValue()).sumCurrentProductsAmount(product.getPrice());
                    break;
                default:
                    break;
            }
        }
    }
}
