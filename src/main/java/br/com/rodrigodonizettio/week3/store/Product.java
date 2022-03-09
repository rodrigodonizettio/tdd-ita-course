package br.com.rodrigodonizettio.week3.store;

public class Product {
    private String name;
    private Integer price;

    public Product(String name, Integer value) {
        this.name = name;
        this.price = value;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
