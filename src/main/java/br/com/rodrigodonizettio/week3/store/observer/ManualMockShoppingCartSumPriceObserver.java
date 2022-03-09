package br.com.rodrigodonizettio.week3.store.observer;

public class ManualMockShoppingCartSumPriceObserver implements ShoppingCartPriceObserver {
    public static final String OBSERVER_TYPE = "SUM-PRICE";
    private Integer sum = 0;

    @Override
    public void sumCurrentProductsAmount(Integer price) {
        sum += price;
    }

    @Override
    public Integer getSumCurrentProductsPrice() {
        return sum;
    }

    @Override
    public String getObserverType() {
        return OBSERVER_TYPE;
    }
}
