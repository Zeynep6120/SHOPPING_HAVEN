package com.tpe.service;

import com.tpe.domain.Product;

public interface ZIProductService {
    // Add a product to the product list
    void addProduct();

    // List a product by its ID
    void listIdProduct(String productCode);

    // List all products in a formatted table
    void listProduct();

    // Delete a product by its ID
    void deleteProductById();

    // List products with sorting options
    void listProductWithSorting();

    // Find a product by its ID
    Product findProductById(String productCode);

    // Search for a product by its name
    void searchProductByName();
}
