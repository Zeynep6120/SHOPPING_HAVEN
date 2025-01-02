package com.tpe.service;

import com.tpe.domain.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService extends HelperMethods implements ZIProductService{

    private static ProductService instance; // Singleton örneği
    public List<Product> productList = new ArrayList<>();
    public Scanner sc = new Scanner(System.in);
    private ProductService() {}

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }
    public void addProduct( ) {

        int select = 1;
        do {

            Product product = new Product();

            // Ürün bilgilerini kullanıcıdan al
            System.out.print("Product name: ");
            product.setProductName(sc.nextLine().toUpperCase().trim());
            System.out.print("Product Category:");
            product.setCategory(sc.nextLine().toUpperCase().trim());
            System.out.print("Product price: ");
            try {
                product.setPrice(sc.nextInt());
            } catch (Exception e) {
                System.out.println("Please enter a numeric value");
                select = 0;
                break;
            }
            sc.nextLine();
            System.out.print("Product size: ");
            product.setSize(sc.nextLine().toUpperCase().trim());
            System.out.print("Product color: ");
            product.setColor(sc.nextLine().toUpperCase().trim());
            System.out.print("Product material: ");
            product.setMaterial(sc.nextLine().toUpperCase().trim());
            System.out.print("Product arm type: ");
            product.setArmType(sc.nextLine().toUpperCase().trim());
            System.out.print("Product length: ");
            product.setLength(sc.nextInt());
            sc.nextLine();

            //Ürün Teknik Alanı
            System.out.print("Product stock: ");
            product.setStock(sc.nextInt());
            sc.nextLine();
            System.out.print("Product manufacturer: ");
            product.setManufacturer(sc.nextLine().toUpperCase().trim());

            // Aynı ürünün mevcut olup olmadığını kontrol et

            for (Product existingProduct : productList) {
                if (existingProduct.getProductName().equals(product.getProductName())
                        && existingProduct.getCategory().equals(product.getCategory())
                        && existingProduct.getPrice().equals(product.getPrice())
                        && existingProduct.getSize().equals(product.getSize())
                        && existingProduct.getColor().equals(product.getColor())
                        && existingProduct.getMaterial().equals(product.getMaterial())
                        && existingProduct.getArmType().equals(product.getArmType())
                        && existingProduct.getLength().equals(product.getLength())
                        && existingProduct.getManufacturer().equals(product.getManufacturer())
                ) {
                    System.out.println("\033[31mThis product already exists.\033[0m");
                }
            }

            createProductId(product);

            // Ürünü Liste ekle
            productList.add(product);

            // Ürün bilgilerini tablo formatında yazdır
            printProductTableHeader();
            printProductTableRow(product);

            // İşleme devam veya çıkış seçeneği sunma
            System.out.println("\033[1;32mPress 1 to CONTINUE the process or 0 to EXIT.\033[0m");
            try {
                select = sc.nextInt();
                sc.nextLine();  // Satır sonu karakterini temizlemek için
            } catch (InputMismatchException e) {
                System.out.println("\033[31mInvalid input! Please enter 0 or 1.\033[0m");
                sc.nextLine();  // Hatalı girişi temizlemek için
                select = 1;     // Hatalı giriş durumunda döngüyü devam ettirmek için
            }

        } while (select != 0);

        listProduct();
    }
    public void listIdProduct(String productCode) {

        boolean found = false;

        // Başlık yazdırma (Tablo başlıkları)
        System.out.printf("\033[1;34m+------------------------------------------------------------+\n");
        System.out.printf("| %-12s | %-10s | %-10s | %-12s | %-5s | %-8s |\n",
                "Product Code", "Product Name", "Manufacturer", "Stock Status", "Price", "Category");
        System.out.printf("+------------------------------------------------------------+\n");


        for (Product product : productList) {
            if (product.getProductCode().equalsIgnoreCase(productCode)) {
                found = true;
                System.out.printf("| %-10s | %-8s | %-8s | %-12d | %-5d | %-8s |%n", product.getProductCode(), product.getProductName(), product.getManufacturer(), product.getStock(), product.getPrice(), product.getCategory());

            }
        }
        System.out.println("+------------------------------------------------------------+");

        if (!found) {
            System.err.println("This product does not exist");
        }

    }
    // ürünleri listeler
    // Renkli yazı ve tablo oluşturma
    public void listProduct() {

        // Başlık yazdırma (Tablo başlıkları)
        System.out.printf("\033[1;34m+------------------------------------------------------------+\n");
        System.out.printf("| %-10s | %-8s | %-8s | %-12s | %-5s | %-8s |\n",
                "Product Code", "Product Name", "Manufacturer", "Stock Status", "Price", "Category");
        System.out.printf("+------------------------------------------------------------+\n");


        for (Product product : productList) {
            System.out.printf("| %-10s | %-8s | %-8s | %-12d | %-5d | %-8s |%n", product.getProductCode(), product.getProductName(), product.getManufacturer(), product.getStock(), product.getPrice(), product.getCategory());
        }
        System.out.println("+------------------------------------------------------------+");
    }
    //System.out.println("4- id si verilen ürünü silme ");
    // Ürün Silme
    public void deleteProductById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the product ID you want to delete: ");
        String productCode = scanner.nextLine().trim();

        Product product = productList.stream().filter(t -> t.getProductCode().equals(productCode)).findFirst().orElse(null);

        if (product!=null) {
            productList.remove(product);
            listProduct();
            System.out.println("The item was deleted successfully.");
            System.err.println("No product found with this ID.");
        }
    }

    //System.out.println("5- Ürünleri filtreleme (max,min,A dan - Z ye )");
    public void listProductWithSorting() {
        // Kullanıcıdan sıralama tercihini al
        Scanner sc = new Scanner(System.in);
        int select = -1;

        while (select == -1) { // Geçerli bir seçim yapılana kadar döngü
            try {
                System.out.println("Choose sorting criteria: ");
                System.out.println("1. Sort by Quantity");
                System.out.println("2. Sort by Product Name");
                System.out.println("3. Sort by Manufacturer Name");  // Üretici ismine göre sıralama seçeneği
                System.out.println("4. Exit"); // Çıkış seçeneği ekledik
                System.out.print("Enter your choice (1-4): ");
                select = sc.nextInt();

                // Geçersiz seçim kontrolü
                if (select < 1 || select > 4) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    select = -1; // Döngüyü tekrar çalıştırmak için choice değeri sıfırlanır
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number between 1 and 4.");
                sc.nextLine(); // Geçersiz girdiyi temizlemek için
                select = -1; // Döngüyü tekrar çalıştırmak için choice değeri sıfırlanır
            }

            // Seçilen tercihe göre sıralama
            switch (select) {
                case 1: // Quantity göre sıralama
                    maximum(productList);
                    break;
                case 2: // Ürün ismine göre sıralama
                    sortAorZ(productList);
                    break;
                case 3: // Üretici ismine göre sıralama
                    sortByManufacturer(productList);
                    break;
                case 4: // Çıkış
                    System.out.println("Exiting the sorting menu.");
                    break;
            }

            // Eğer çıkış seçeneği seçildiyse, while döngüsünden çıkılır
            if (select == 4) {
                break;
            }
        }
    }


    public Product findProductById(String productCode) {
        return productList.stream().filter(t->t.getProductCode().equalsIgnoreCase(productCode)).findAny().orElse(null); // Eğer ürün varsa döner, yoksa null
    }

    public void searchProductByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the product name you want to search: ");
        String productName = scanner.nextLine().trim();

        if (productList == null || productList.isEmpty()) {
            System.err.println("The product list is empty.");
        }

        boolean found = false;

        // Başlık yazdırma (Tablo başlıkları)
        System.out.printf("\033[1;34m+------------------------------------------------------------+\n");
        System.out.printf("| %-10s | %-8s | %-8s | %-12s | %-5s | %-8s |\n",
                "Product Code", "Product Name", "Manufacturer", "Stock Status", "Price", "Category");
        System.out.printf("+------------------------------------------------------------+\n");



        for (Product product : productList) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                found = true;
                System.out.printf("| %-10s | %-8s | %-8s | %-12d | %-5d | %-8s |%n", product.getProductCode(), product.getProductName(), product.getManufacturer(), product.getStock(), product.getPrice(), product.getCategory());
            }
        }
        System.out.println("+------------------------------------------------------------+");

        if (!found) {
            System.err.println("No product was found with the name you were looking for.");
        }
    }


}


