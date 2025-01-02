package com.tpe.service;

import java.util.Scanner;

public class ValidationMethods implements  ZIValidationMethods{

    Scanner scanner = new Scanner(System.in);
    private final String RESET = "\033[0m";
    private final String RED = "\033[31m";

    public String validateStringNotEmpty(String input, String errorMessage) {
        while (input.trim().isEmpty()) {
            System.out.println(RED + errorMessage + RESET);
            input = scanner.nextLine();
        }
        return input.trim();
    }

    public String validatePhone(String phone) {
        while (!phone.matches("\\d{3}-\\d{3}-\\d{4}")) {
            System.out.println(RED + "Please enter a valid phone number (ex: 555-123-4567)." + RESET);
            phone = scanner.nextLine();
        }
        return phone;
    }

    public boolean validateEmail(String email) {
        boolean isValid = true;
        boolean hasSpace = email.contains(" ");
        boolean hasAtSymbol = email.contains("@");

        if (hasSpace) {
            System.out.println(RED + "Email cannot contain spaces" + RESET);
            isValid = false;
        } else if (!hasAtSymbol) {
            System.out.println(RED + "Email must contain @ symbol" + RESET);
            isValid = false;
        } else {
            String firstPart = email.split("@")[0];
            String secondPart = email.split("@")[1];

            boolean isExistInvalidChar = !firstPart.replaceAll("[A-Za-z0-9-._]", "").isEmpty();
            boolean isValidDomain = secondPart.equals("gmail.com") ||
                    secondPart.equals("yahoo.com") ||
                    secondPart.equals("hotmail.com");

            if (isExistInvalidChar) {
                System.out.println(RED + "Email username can only contain letters, digits, and -._ characters!" + RESET);
                isValid = false;
            } else if (!isValidDomain) {
                System.out.println(RED + "You can only register with gmail, yahoo, or hotmail domains!" + RESET);
                isValid = false;
            }

        }
        return isValid;
    }

    public boolean validatePassword(String password) {
        boolean isValid;

        boolean hasSpace = password.contains(" ");
        boolean isLengthGtSix = password.length() >= 6;
        boolean upperLetter = !password.replaceAll("[^A-Z]", "").isEmpty();
        boolean lowerLetter = !password.replaceAll("[^a-z]", "").isEmpty();
        boolean digits = !password.replaceAll("[\\D]", "").isEmpty();
        boolean symbols = !password.replaceAll("[\\P{Punct}]", "").isEmpty();

        if (hasSpace) {
            System.out.println(RED + "Password cannot contain spaces!" + RESET);
        } else if (!isLengthGtSix) {
            System.out.println(RED + "Password must be at least 6 characters long!" + RESET);
        } else if (!upperLetter) {
            System.out.println(RED + "Password must contain an uppercase letter!" + RESET);
        } else if (!lowerLetter) {
            System.out.println(RED + "Password must contain a lowercase letter!" + RESET);
        } else if (!digits) {
            System.out.println(RED + "Password must contain a digit!" + RESET);
        } else if (!symbols) {
            System.out.println(RED + "Password must contain a symbol!" + RESET);
        }


        isValid = !hasSpace && isLengthGtSix && upperLetter && lowerLetter && digits && symbols;

        if (!isValid) {
            System.out.println(RED + "Password is invalid, try again!" + RESET);
        }
        return isValid;     }
}
