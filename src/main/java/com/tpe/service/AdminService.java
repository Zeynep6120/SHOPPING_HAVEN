package com.tpe.service;

import com.tpe.domain.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService implements ZIAdminService {
    Scanner scanner = new Scanner(System.in);
    private List<Admin> adminList = new ArrayList<>();
    private final ProductService productService = ProductService.getInstance();
    public void adminMenu() {
        boolean isExist = false;
        while (!isExist) {
            System.out.println("========================================================");
            System.out.println("1-Admin Dashboard: ");
            System.out.println("2-Admin Register");
            System.out.println("0-EXIT");

            int select = scanner.nextInt();
            scanner.nextLine();

            switch (select) {
                case 1:
                    registeredAdminDashboard();
                    break;

                case 2:
                    register();
                    break;
                case 0:
                    isExist = true;
                    System.out.println("Have a stylish day...");
                    break;

                default:
                    System.out.println("Error!!! Please select again");
                    break;
            }
        }
    }

    public void registeredAdminDashboard() {
        int select;
        boolean isExist = false;

        System.out.println("Enter your email :");
        String email = scanner.nextLine();
        if (!adminList.stream().map(Admin::getEmail).toList().contains(email) ){
            System.out.println("There is no such admin with this email, please register first.");
        }

        System.out.println("Enter your password :");
        String password = scanner.nextLine();

        Admin admin = adminList.stream().filter(t-> t.getEmail().equals(email)).findFirst().orElse(null);
        if (admin!=null && !(admin.getPassword().equals(password))){
            System.out.println("Password incorrect");
            adminMenu();
        }

        while (!isExist) {

            System.out.println("========= Warehouse Management ==========");
            System.out.println("1- Enter a product");
            System.out.println("2- Get product by Id");
            System.out.println("3- List all products");
            System.out.println("4- Delete product by Id");
            System.out.println("5- Sort products (max,min,A - Z)");
            System.out.println("0- EXIT");

            select = scanner.nextInt();
            scanner.nextLine();

            switch (select) {
                case 1:
                    productService.addProduct();
                    break;
                case 2:
                    System.out.print("Enter the product code:");
                    String productCode = scanner.nextLine();
                    productService.listIdProduct(productCode);
                    break;
                case 3:
                    productService.listProduct();
                    break;
                case 4:
                    productService.deleteProductById();
                    break;
                case 5:
                    productService.listProductWithSorting();
                    break;
                case 0:
                    System.out.println("Have a nice day...");
                    isExist = true;
                    break;
                default:
                    System.out.println("Invalid selection, try again.");
            }
        }
    }

    public void register(){

        Admin admin = new Admin();
        System.out.println("Name :");
        admin.setName(scanner.nextLine());

        System.out.println("Email : " );
        admin.setEmail(scanner.nextLine());

        System.out.println("Password : ");
        admin.setPassword(scanner.nextLine());

        adminList.add(admin);
        System.out.println(admin);

    }
}
