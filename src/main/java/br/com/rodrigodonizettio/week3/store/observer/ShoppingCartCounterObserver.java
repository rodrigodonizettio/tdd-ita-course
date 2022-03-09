package br.com.rodrigodonizettio.week3.store.observer;

public interface ShoppingCartCounterObserver extends ShoppingCartObserver {
    void countCurrentProductsAmount();

    Integer getCurrentProductsAmount();
}
