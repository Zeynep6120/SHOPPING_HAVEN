package com.tpe.service;

public interface ZIValidationMethods {
    // Validate that the input string is not empty
    String validateStringNotEmpty(String input, String errorMessage);

    // Validate phone number format (XXX-XXX-XXXX)
    String validatePhone(String phone);

    // Validate email format and domain
    boolean validateEmail(String email);

    // Validate password criteria (length, uppercase, lowercase, digit, symbol)
    boolean validatePassword(String password);
}
