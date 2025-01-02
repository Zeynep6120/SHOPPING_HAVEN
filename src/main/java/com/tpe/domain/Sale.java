package com.tpe.domain;


import java.util.List;
public class Sale {
    private User user;               // Alışı gerçekleştiren kullanıcı
    private List<CartItem> cartItemList;   // Satın alınan ürünler
    private Double totalPrice;       // Toplam fiyat
    private String paymentStatus;     // Ödeme durumu (Ödendi, Bekliyor vb.)
    private String deliveryStatus;      // Kargo durumu (Yola çıktı, Teslim edildi vb.)

    // Constructor
    public Sale(User user, List<CartItem> productList, double totalPrice) {
        this.user = user;
        this.cartItemList = productList;
        this.totalPrice = totalPrice;
        this.paymentStatus = "Pending"; // Başlangıçta ödeme bekliyor
        this.deliveryStatus = "Pending"; // Başlangıçta kargo bekliyor
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getProductList() {
        return cartItemList;
    }

    public void setProductList(List<CartItem> productList) {
        this.cartItemList = productList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "user=" + user.getUserName() +
                ", productList=" + cartItemList +
                ", totalPrice=" + totalPrice +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
        }
    }


