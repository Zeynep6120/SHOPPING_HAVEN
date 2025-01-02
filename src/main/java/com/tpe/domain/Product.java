package com.tpe.domain;

import java.util.ArrayList;
import java.util.List;

/*
* Bir kıyafet ürünü için kullanabileceğiniz alanlar (fields) şunlardır:

### Temel Alanlar:
1. **Urun Adı (Product Name)**: Ürünün adı. Örn: "Klasik Beyaz Gömlek"
2. **Urun Kodu (Product Code)**: Stok takibi ve benzersizlik için özel kod. Örn: "BG-2024-001"
3. **Kategori (Category)**: Ürünün hangi kategoride yer aldığı. Örn: "Kadın Gömlek", "Erkek Pantolon".
4. **Fiyat (Price)**: Ürünün satış fiyatı. Örn: "499.99 TL"
5. **Para Birimi (Currency)**: Fiyatın hangi para biriminde olduğu. Örn: "TL", "USD".
*/
public class Product {
    private String productCode;
    private String productName;
    private String category;
    private Integer price;
    private String size;
    private String color;
    private String material;
    private String armType;
    private Integer length;
    private int stock;
    private String manufacturer;
    private List<Integer> ratingList;
    private Integer rating;
    private List<String> commentList;

    public Product() {}

    public Product(String productName, String category, Integer price, String size, String color, String material, String armType, Integer length, int stock, String manufacturer) {
        //super(ürünAdı, kategori, fiyat, beden, renk, malzeme, kolTipi, boyUzunlugu, stokDurumu, uretici);

        this.productName = productName;
        this.category = category;
        this.price = price;
        this.size = size;
        this.color = color;
        this.material = material;
        this.armType = armType;
        this.length = length;
        this.stock = stock;
        this.manufacturer = manufacturer;
        this.ratingList = new ArrayList<>();
        this.rating = 0;
        this.commentList = new ArrayList<>();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getArmType() {
        return armType;
    }

    public void setArmType(String armType) {
        this.armType = armType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Integer> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Integer> ratingList) {
        this.ratingList = ratingList;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<String> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductName='" + productName + '\'' +
                ", ProductCode='" + productCode + '\'' +
                ", Category='" + category + '\'' +
                ", Price=" + price +
                ", Size='" + size + '\'' +
                ", Color='" + color + '\'' +
                ", Material='" + material + '\'' +
                ", SleeveType='" + armType + '\'' +
                ", Length='" + length + '\'' +
                ", StockStatus=" + stock +
                ", Manufacturer='" + manufacturer + '\'' +
                '}';
    }

}
