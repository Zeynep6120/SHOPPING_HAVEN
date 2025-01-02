package com.tpe.domain;

import java.time.LocalDate;
import java.util.List;

/*Sepet İşlemleri:
Sepete Eklenen Miktar (Cart Quantity): Kullanıcının sepete eklediği ürün miktarı.

Örn: 2 adet "Klasik Beyaz Gömlek".
Sepet ID (Cart ID): Sepeti benzersiz şekilde tanımlayan ID.

Örn: Kullanıcının farklı sepet oturumlarını izlemek için kullanılabilir.*/
public class Cart  {
    private List<CartItem> cartItemList;  // Ürünler listesi
    private Integer totalPrice;
    private LocalDate localDate;
    private String deliveryPrice;      // Kargo ücreti

    public Cart() {
    }

    public Cart(List<CartItem> cartItemList, LocalDate localDate, String deliveryPrice) {
        this.cartItemList = cartItemList;
        this.localDate = localDate;
        this.deliveryPrice = deliveryPrice;
        Integer x= 0;
        for (CartItem item : cartItemList) {

            x += item.getPrice();

        }
        this.totalPrice = x;

    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Cart: \n");
        for (CartItem item : cartItemList) {

            sb.append(item.getProduct().getProductName()).append(" - ").append(item.getProduct().getPrice()).append(" TL\n");

        }

        sb.append("Total Price: ").append(" TL\n");

        sb.append("Delivery Price: ").append(deliveryPrice = totalPrice < 500 ? "50" : "0").append("\n");

        return sb.toString();
    }


}




