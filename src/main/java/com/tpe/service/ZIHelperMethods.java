package com.tpe.service;

import com.tpe.domain.Product;

import java.util.List;

public interface ZIHelperMethods {
    // Create a product ID based on the product's name and current time
    void createProductId(Product product);

    // Print the product table header
    void printProductTableHeader();

    // Print a row for each product in the table
    void printProductTableRow(Product product);

    // Sort and display products by price in descending order
    void maximum(List<Product> productList);

    // Sort and display products by price in ascending order
    void minimum(List<Product> productList);

    // Sort and display products alphabetically by name (A to Z)
    void sortAorZ(List<Product> productList);

    // Sort and display products by the manufacturer's name
    void sortByManufacturer(List<Product> productList);
}
