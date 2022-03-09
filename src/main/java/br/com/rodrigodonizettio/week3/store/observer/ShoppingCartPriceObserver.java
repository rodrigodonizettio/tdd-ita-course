package br.com.rodrigodonizettio.week3.store.observer;

public interface ShoppingCartPriceObserver extends ShoppingCartObserver {
    void sumCurrentProductsAmount(Integer price);

    Integer getSumCurrentProductsPrice();
}
