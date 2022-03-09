package br.com.rodrigodonizettio.week3.store.observer;

public class ManualMockShoppingCartAmountCounterObserver implements ShoppingCartCounterObserver {
    public static final String OBSERVER_TYPE = "AMOUNT-COUNTER";
    private Integer counter = 0;

    @Override
    public void countCurrentProductsAmount() {
        counter++;
    }

    @Override
    public Integer getCurrentProductsAmount() {
        return counter;
    }

    @Override
    public String getObserverType() {
        return OBSERVER_TYPE;
    }
}
