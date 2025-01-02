package com.tpe.domain;

public class CartItem {

    private Product product;

    private Integer quantity;

    private Integer price;

    public CartItem(Product product, Integer quantity, Integer price) {
        this.product = product;
        this.quantity = quantity;
        this.price = (product.getPrice() * quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
