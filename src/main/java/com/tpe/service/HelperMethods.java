package com.tpe.service;

import com.tpe.domain.Product;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HelperMethods{

    // Ürün ID'sini oluşturur
    public void createProductId(Product product) {
        try {
            // ID'yi ürün adı ve yıl bilgisiyle oluştur, counter'ı kullanarak benzersiz yap
            product.setProductCode(product.getProductName().toUpperCase().substring(0, 3) + LocalDate.now().getYear() + LocalTime.now().getSecond());

        } catch (Exception e) {
            // Eğer ürün adı kısa ise "NULL" kullanarak ID oluştur
            product.setProductCode(product.getProductName() + LocalDate.now().getYear()+ LocalTime.now().getSecond());

        }
    }

    public void printProductTableHeader() {
        System.out.println("\033[1;34m+------------------------------------------------------------+");
        System.out.println("| Ürün Kodu | Ürün Adı | Üretici | Stok Durumu | Fiyat | Kategori |");
        System.out.println("+------------------------------------------------------------+");
    }

    // Tablo satırı yazdırma
    public void printProductTableRow(Product product) {
        String productCode = product.getProductCode();
        String productName = product.getProductName();
        String producer = product.getManufacturer();
        int stock = product.getStock();
        int price = product.getPrice();
        String category = product.getCategory();

        // Stok durumu 0 ise kırmızı renkte yazdır
        if (stock == 0) {
            System.out.printf("\033[31m| %-10s | %-8s | %-8s | %-12s | %-5d | %-8s |\033[0m\n", productCode, productName, producer, "STOK YOK", price, category);

        } else {
            System.out.printf("| %-10s | %-8s | %-8s | %-12d | %-5d | %-8s |\n", productCode, productName, producer, stock, price, category);

        }
    }

    public void maximum(List<Product> productList) {
        // Büyükten küçüğe sıralama (fiyatlara göre)
        List<Product> sortedByMaxPrice = productList.stream()
                .sorted(Comparator.comparingInt(Product::getPrice).reversed())
                .toList();

        System.out.println("Products listed from largest to smallest: " + sortedByMaxPrice);

        sortedByMaxPrice
                .forEach(product -> {
                    System.err.println("\nProduct Code: " + product.getProductCode() +
                            "\nProduct Name: " + product.getProductName() +
                            "\nPrice: " + product.getPrice() +
                            "\nCategory: " + product.getCategory() +
                            "\nSize: " + product.getSize() +
                            "\nColor: " + product.getColor() +
                            "\nMaterial: " + product.getMaterial() +
                            "\nSleeve Type: " + product.getArmType() +
                            "\nLength: " + product.getLength() +
                            "\nManufacturer: " + product.getManufacturer() +
                            "\nStock Status: " + product.getStock() +
                            "\n");

                });
    }
    public void minimum(List<Product> productList) {
        // Küçükten büyüğe sıralama (fiyatlara göre)
        List<Product> sortedMinPrice = productList.stream()
                .sorted(Comparator.comparingInt(Product::getPrice))
                .toList();

        System.out.println("Products listed from smallest to largest:" + sortedMinPrice);

        // En düşük fiyatlı ürünleri yazdırma
        sortedMinPrice
                .forEach(product -> {
                    // Ürün bilgilerini yazdırma
                    System.err.println("\nProduct Code: " + product.getProductCode() +
                            "\nProduct Name: " + product.getProductName() +
                            "\nPrice: " + product.getPrice() +
                            "\nCategory: " + product.getCategory() +
                            "\nSize: " + product.getSize() +
                            "\nColor: " + product.getColor() +
                            "\nMaterial: " + product.getMaterial() +
                            "\nSleeve Type: " + product.getArmType() +
                            "\nLength: " + product.getLength() +
                            "\nManufacturer: " + product.getManufacturer() +
                            "\nStock Status: " + product.getStock() +
                            "\n");

                });
    }

    public void sortAorZ(List<Product> productList) {

        // A'dan Z'ye sıralama (ürün adlarına göre)
        List<Product> sortedAZ = productList.stream()
                .sorted(Comparator.comparing(Product::getProductName))
                .toList();

        System.out.println("Products listed from A to Z:" + sortedAZ);

        sortedAZ
                .forEach(product -> {
                    System.err.println("\nProduct Code: " + product.getProductCode() +
                            "\nProduct Name: " + product.getProductName() +
                            "\nPrice: " + product.getPrice() +
                            "\nCategory: " + product.getCategory() +
                            "\nSize: " + product.getSize() +
                            "\nColor: " + product.getColor() +
                            "\nMaterial: " + product.getMaterial() +
                            "\nSleeve Type: " + product.getArmType() +
                            "\nLength: " + product.getLength() +
                            "\nManufacturer: " + product.getManufacturer() +
                            "\nStock Status: " + product.getStock() +
                            "\n");

                });
    }

    public void sortByManufacturer(List<Product> productList) {
        // Sıralama işlemi: Ürünleri üretici ismine göre alfabetik sıralar
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                // Üretici ismi karşılaştırması
                return p1.getManufacturer().compareTo(p2.getManufacturer());
            }
        });

        // Sıralama işleminden sonra listeyi yazdırmak
        System.out.println("Products sorted by Manufacturer Name:");
        for (Product product : productList) {
            System.out.printf("| %-10s | %-8s | %-8s | %-12s | %-5d | %-8s |\n",
                    product.getProductCode(),
                    product.getProductName(),
                    product.getManufacturer(),
                    "Stock", // replace with actual stock value
                    product.getPrice(),
                    product.getCategory());
        }
    }
}
