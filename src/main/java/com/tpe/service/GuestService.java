package com.tpe.service;

import java.util.Scanner;

public class GuestService implements ZIGuestService{

    Scanner input = new Scanner(System.in);
    private final ProductService productService = ProductService.getInstance();
    public  CustomerService customerService = new CustomerService();
    public void guestMenu() {
        boolean isExist = false;
        while (!isExist) {
            System.out.println("========================================================");
            System.out.println("1-Search by Product Name");
            System.out.println("2-Filter Products (max, min, A to Z)");
            System.out.println("3-Redirect to Customer Registration");
            System.out.println("0-EXIT");

            int select = input.nextInt();
            input.nextLine();

            switch (select) {
                case 1:
                    productService.searchProductByName();
                    break;
                case 2:
                    productService.listProductWithSorting();
                    break;
                case 3:
                    customerService.register();
                    break;
                case 0:
                    isExist = true;
                    System.out.println("Have a nice day...");
                    break;
                default:
                    System.out.println("Error!!!");
                    break;
            }
        }
    }
}
