package com.tpe.service;

import com.tpe.domain.User;

public interface ZICustomerService {
    void customerMenu(); // Show the customer menu and allow navigation through options.

    User login(); // Login process for the customer.

    void register(); // Customer registration method.

    void displayUserTable(User registeredUser); // Display the user's information in a table format.

    void giveStar(); // Give a rating to a product.

    void addComment(); // Add a comment to a product
}
