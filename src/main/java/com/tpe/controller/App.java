package com.tpe.controller;


import com.tpe.service.*;

import java.util.*;

public class App {
    public Scanner input = new Scanner(System.in);
    private final AdminService adminService = new AdminService();
    private final CustomerService customerService = new CustomerService();
    private final GuestService guestService = new GuestService();

    public void displayAppMenu() {
            int select;

            do {
                System.out.println("========================================================");
                System.out.println("Welcome to StyleHaven!!!");
                System.out.println("1-Admin Module");
                System.out.println("2-Customer Module");
                System.out.println("3-Guest Module");
                System.out.println("0-EXIT");
                System.out.print("Select a module: ");
                select = input.nextInt();

                switch (select) {
                    case 1:
                        System.out.println("Admin Module");
                        adminService.adminMenu();
                        break;
                    case 2:
                        System.out.println("Customer Module");
                        customerService.customerMenu();
                        break;
                    case 3:
                        System.out.println("Guest Module");
                        guestService.guestMenu();
                        break;
                    case 0:
                        System.out.println("Have a stylish day...");
                        break;
                    default:
                        System.out.println("Error!!! Please select again");
                        break;
                }
            } while (select != 0);
        }


    }






