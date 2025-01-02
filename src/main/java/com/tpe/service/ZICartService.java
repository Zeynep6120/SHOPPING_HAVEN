package com.tpe.service;

import com.tpe.domain.Cart;
import com.tpe.domain.CartItem;
import com.tpe.domain.User;

import java.util.List;

public interface ZICartService {
    // Sepete ürün ekleme
    void addToCart(User customer);

    // Ödeme işlemi
    void payment(User customer, Cart cart);

    // Kredi kartı ile ödeme işlemi
    void creditCardPayment(User customer, Cart cart);

    // Kapıda ödeme işlemi
    void cashOnDeliveryPayment(User customer, Cart cart);

    // Satın alınan ürünlerin stoklarını güncelleme
    void updateProductStock(Cart cart);


    // Sepetteki ürünleri listeleme
    void listCart(List<CartItem> cartItemList);
}
