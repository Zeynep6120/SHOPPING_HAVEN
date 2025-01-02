package com.tpe.service;

import com.tpe.domain.*;

import java.time.LocalDate;
import java.util.*;

public class CartService implements ZICartService{
    Scanner scanner = new Scanner(System.in);
    private final ProductService productService = ProductService.getInstance();
    private  List<Cart> cartList = new ArrayList<>();
    Cart cart;
    // Sepete ürün ekleme
    public void addToCart(User customer) {
        List<CartItem> cartItemList = new ArrayList<>();
        boolean continueAdding = true;  // Kullanıcının ürün eklemeye devam edip etmeyeceğini kontrol etmek için

        while (continueAdding) {
            productService.listProduct();  // Ürünleri listele
            System.out.println("Please enter the product code of the item you want:");
            String productCode = scanner.nextLine();  // Kullanıcıdan ürün kodunu al
            Product selectedProduct = productService.findProductById(productCode);  // Ürünü bul

            // Ürün bulunamadıysa döngüye devam et
            if (selectedProduct == null) {
                System.out.println("Invalid product code. Please try again.");
                continue;
            }

            System.out.println("Please enter the amount you wish to add:");
            Integer quantity = scanner.nextInt();  // Kullanıcıdan miktar al
            scanner.nextLine();  // Satır sonrasındaki boşluğu temizle

            CartItem cartItem = new CartItem(selectedProduct, quantity, selectedProduct.getPrice());
            cartItemList.add(cartItem);  // Sepete ürün ekle

            String deliveryPrice = cartItem.getPrice() < 500 ? "50" : "0";  // Kargo ücreti hesapla
            Cart cart = new Cart(cartItemList, LocalDate.now(), deliveryPrice);

            System.out.println("Product added to cart: " + selectedProduct.getProductName());
            listCart(cartItemList);  // Sepetteki ürünleri listele

            // Kullanıcıya başka ürün eklemek isteyip istemediğini sor
            System.out.println("Do you want to add another product to your cart? (yes/no):");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                continueAdding = false;  // "no" cevabı alınca döngüyü sonlandır
            }
        }

        // Sepetle ödeme işlemi
        payment(customer, cart);
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



    public void giveStar() {
        System.out.println("Please select the product you want to rate");
        productService.listProduct();
        System.out.println("Please enter the product code of the item you want");
        String productCode = scanner.nextLine();
        Product selectedProduct = productService.findProductById(productCode);
        System.out.println("Please rate our product");
        int star = scanner.nextInt();
        // Yıldız değeri geçerli bir aralıkta olmalı (1-5 arası)
        if (star >= 1 && star <= 5) {
            selectedProduct.getRatingList().add(star);
            selectedProduct.setRating((selectedProduct.getRatingList().stream().mapToInt(Integer::intValue).sum()) / (selectedProduct.getRatingList().size()));
            System.out.println("Yıldız verildi: " + star + " yıldız.");
        } else {
            System.out.println("Geçersiz yıldız değeri! Lütfen 1 ile 5 arasında bir sayı girin.");
        }
    }

    public void addComment() {
        // Kullanıcıdan yorum yapılacak ürünü seçmesini isteyin
        System.out.println("Please select the product you want to comment on:");
        productService.listProduct();  // Ürünleri listele
        System.out.print("Enter the product code of the item you want: ");
        String productCode = scanner.nextLine();

        // Seçilen ürün
        Product selectedProduct = productService.findProductById(productCode);

        // Eğer ürün bulunamazsa
        if (selectedProduct == null) {
            System.out.println("The product code you entered does not exist.");
            return;
        }

        // Kullanıcıdan yorum al
        System.out.println("Please enter your comment for the product:");
        String comment = scanner.nextLine();

        // Yorumun boş olup olmadığını kontrol et
        if (comment.trim().isEmpty()) {
            System.out.println("Comment cannot be empty. Please enter a valid comment.");
            return;
        }

        // Ürüne yorumu ekle
        if (selectedProduct.getCommentList() == null) {
            selectedProduct.setCommentList(new ArrayList<>());
        }
        selectedProduct.getCommentList().add(comment);  // Yorum ekle

        // Yorum eklenip eklenmediğini göster
        System.out.println("Your comment has been added to the product: " + selectedProduct.getProductName());
    }

    // Sepetteki ürünleri listeleme
    public void listCart(List<CartItem> cartItemList) {
        System.out.println("Items in the cart:");
        for (CartItem item : cartItemList) {
            System.out.println(item.getProduct().getProductName() + " - " + item.getProduct().getPrice() + " TL");
        }
    }


}




















