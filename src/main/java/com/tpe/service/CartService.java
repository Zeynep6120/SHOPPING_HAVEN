package com.tpe.service;

import com.tpe.domain.*;

import java.time.LocalDate;
import java.util.*;

public class CartService implements ZICartService{
    Scanner scanner = new Scanner(System.in);
    private final ProductService productService = ProductService.getInstance();
    private  List<Cart> cartList = new ArrayList<>();
     public Cart cart;
    // Sepete ürün ekleme
    public void addToCart(User customer) {
        List<CartItem> cartItemList = new ArrayList<>();
        boolean continueAdding = true;

        while (continueAdding) {
            productService.listProduct();  // Ürünleri listele
            System.out.println("Please enter the product code of the item you want:");
            String productCode = scanner.nextLine().trim();
            Product selectedProduct = productService.findProductById(productCode);

            if (selectedProduct == null) {
                System.out.println("Invalid product code. Please try again.");
                continue;
            }

            System.out.println("Please enter the amount you wish to add:");
            Integer quantity = scanner.nextInt();
            scanner.nextLine();  // Satır sonrasındaki boşluğu temizle

            // CartItem oluşturulurken toplam fiyatı hesapla
            CartItem cartItem = new CartItem(selectedProduct, quantity, selectedProduct.getPrice() * quantity);
            cartItemList.add(cartItem);  // Sepete ürün ekle

            String deliveryPrice = cartItem.getPrice() < 500 ? "50" : "0";  // Kargo ücreti hesapla
            cart = new Cart(cartItemList, LocalDate.now(), deliveryPrice);  // Global cart'ı güncelle

            System.out.println("Product added to cart: " + selectedProduct.getProductName());
            listCart(cartItemList);  // Sepetteki ürünleri listele

            System.out.println("Do you want to add another product to your cart? (yes/no):");
            String response = scanner.nextLine().trim();
            if (response.equalsIgnoreCase("no")) {
                continueAdding = false;
            }
        }

        payment(customer, cart);  // Artık cart değişkeni null olmayacak
    }

    public void payment(User customer, Cart cart) {
        Sale sale = new Sale(customer, cart.getCartItemList(), cart.getTotalPrice());
        boolean validPaymentOption = false;

        // Kullanıcıya ödeme seçeneği soruluyor
        while (!validPaymentOption) {
            System.out.println("Please choose a payment method:");
            System.out.println("1 - Credit Card");
            System.out.println("2 - Cash on Delivery");
            System.out.println("0 - Cancel");
            int paymentOption = scanner.nextInt();
            scanner.nextLine();  // Boşluğu temizle

            switch (paymentOption) {
                case 1:
                    creditCardPayment(customer, cart); // Kredi kartı ödeme işlemi
                    validPaymentOption = true;
                    break;
                case 2:
                    cashOnDeliveryPayment(customer, cart); // Kapıda ödeme işlemi
                    validPaymentOption = true;
                    break;
                case 0:
                    System.out.println("Payment canceled.");
                    return;  // Ödeme iptal edilirse metottan çıkılır
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    // Kredi Kartı ile ödeme işlemi
    public void creditCardPayment(User customer, Cart cart) {
        // Kredi kartı bilgileri burada işlenebilir
        System.out.println("You selected Credit Card payment.");
        // Ödeme işlemi başarıyla tamamlandı
        System.out.println("Payment successfully processed via Credit Card.");

        // Stokları güncelleme işlemi
        updateProductStock(cart);
    }

    // Kapıda Ödeme ile ödeme işlemi
    public void cashOnDeliveryPayment(User customer, Cart cart) {
        // Kapıda ödeme ile ilgili işlemler burada yapılabilir
        System.out.println("You selected Cash on Delivery.");
        // Ödeme işlemi başarıyla tamamlandı
        System.out.println("Payment successfully processed via Cash on Delivery.");

        // Stokları güncelleme işlemi
        updateProductStock(cart);
    }

    // Satın alınan ürünlerin stok durumunu güncelleme
    public void updateProductStock(Cart cart) {
        // Tablo başlıkları
        System.out.printf("%-25s%-20s%-20s%-20s\n", "Product Name", "Current Stock", "Quantity Purchased", "Updated Stock");
        System.out.println("-------------------------------------------------------------------------------------------------------");

        // Ürünlerin stok güncellenmesi
        for (CartItem cartItem : cart.getCartItemList()) {
            Product product = cartItem.getProduct();
            int currentStock = product.getStock(); // Mevcut stok miktarı
            int updatedStock = currentStock - cartItem.getQuantity();  // Satın alınan miktarı stoktan düşüyoruz
            product.setStock(updatedStock);  // Stok güncelleniyor

            // Tabloya yazdırıyoruz
            System.out.printf("%-25s%-20d%-20d%-20d\n", product.getProductName(), currentStock, cartItem.getQuantity(), updatedStock);
        }
    }


    public void listCart(List<CartItem> cartItemList) {
        System.out.println("Items in the cart:");
        for (CartItem item : cartItemList) {
            System.out.println(item.getProduct().getProductName() + " - " + item.getPrice() + " TL");
        }
    }




}




















