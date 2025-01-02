package com.tpe.service;

import com.tpe.domain.Address;
import com.tpe.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService extends ValidationMethods{
    public Scanner input = new Scanner(System.in);
    private final String RESET = "\033[0m";
    private final String RED = "\033[31m";
    private final String GREEN = "\033[32m";
    private final String YELLOW = "\033[33m";
    private final String BLUE = "\033[34m";
    private final String CYAN = "\033[36m";
    private List<User> customerList = new ArrayList<>();
    private List<Address> addressList = new ArrayList<>();
    private final ProductService productService = ProductService.getInstance();
    private final CartService cartService = new CartService();
    public void customerMenu() {
        User customer = login();
        boolean isExist = false;
        while (!isExist) {
            System.out.println("========================================================");
            System.out.println("1-Customer Registration");
            System.out.println("2-Search by Product Name");
            System.out.println("3-Filter Products (max, min, A to Z)");
            System.out.println("4-Select Product and Add to Shopping Cart");
            System.out.println("5-Product Review and Rating");
            System.out.println("0-EXIT");

            int select = input.nextInt();
            input.nextLine();

            switch (select) {
                case 1:
                    register();
                    break;
                case 2:
                    productService.searchProductByName();
                    break;
                case 3:
                    productService.listProductWithSorting();
                    break;
                case 4:
                    cartService.addToCart(customer);
                    break;
                case 5:
                    cartService.giveStar();
                    break;
                case 0:
                    System.out.println("Have a nice day...");
                    isExist = true;
                    break;
                default:
                    System.out.println("Incorrect entry!!!");
                    break;
            }
        }
    }

    public User login() {
        System.out.println("Please login before you can see the menu");
        System.out.println(YELLOW + "Email:" + RESET);
        String email = scanner.nextLine();

        User customer = customerList.stream().filter(t->t.getUserEmail().equals(email)).findAny().orElse(null);

        if (customer!=null) {
            boolean isSuccess = false;
            int attempts = 3;

            while (!isSuccess && attempts > 0) {
                System.out.println(YELLOW + "Enter your password:" + RESET);
                String password = scanner.nextLine();

                if (customer.getUserPassword().equals(password)) {
                    System.out.println(GREEN + "You have successfully logged into the system. Welcome!" + RESET);
                    isSuccess = true;
                    displayUserTable(customer);  // Kullanıcı bilgilerini tablo olarak görüntüle
                } else {
                    attempts--;
                    if (attempts == 0) {
                        System.out.println(RED + "\"You have logged in incorrectly 3 times! You are being redirected to the main menu." + RESET);
                    } else {
                        System.out.println(RED + "Password is incorrect. Your remaining rights are: " + attempts + RESET);
                    }
                }
            }
        } else {
            System.out.println(RED + "No registered users found in the system. Please check your information or register." + RESET);
            register();
        }

        return customer;
    }

    private void displayUserTable(User registeredUser) {

        if (registeredUser != null) {
            // Tablo başlıkları
            System.out.println(BLUE + "\n---------------------------- User Information ----------------------------" + RESET);
            System.out.printf("| %-5s | %-15s | %-15s | %-15s | %-10s | %-15s |\n", "ID", " Name", "Last Name", "Phone", "Email",  "Address");
            System.out.println("------------------------------------------------------------------------");

            // Kullanıcı bilgilerini tablo formatında yazdırma
            System.out.printf("| %-5d | %-15s | %-15s | %-15s | %-10s | %-15s |\n",
                    registeredUser.getUserId(),
                    registeredUser.getUserName(),
                    registeredUser.getUserLastname(),
                    registeredUser.getUserPhone(),
                    registeredUser.getUserEmail(),
                    registeredUser.getAddress().toString()); // Adresin formatlı çıktısı
            System.out.println("------------------------------------------------------------------------");
        } else {
            System.out.println(RED + "User information not found!" + RESET);
        }
    }

    public void register() {
        System.out.println(GREEN + "Enter your name:" + RESET);
        String name = validateStringNotEmpty(input.nextLine(), "Name cannot be empty!");

        String email;
        boolean isValidEmail;
        do {
            System.out.println(GREEN + "Enter your email:" + RESET);
            email = input.nextLine();
            isValidEmail = validateEmail(email);
            String finalEmail = email;
            User customer = customerList.stream().filter(t-> t.getUserEmail().equals(finalEmail)).findAny().orElse(null);
            if (customer!=null) {
                System.out.println(RED + "There is already a registered user with this email!" + RESET);
                isValidEmail = false;
            }
        } while (!isValidEmail);

        String password;
        do {
            System.out.println(GREEN + "Create your password:" + RESET);
            password = scanner.nextLine();
        } while (!validatePassword(password));

        System.out.println(GREEN + "Enter your surname:" + RESET);
        String lastname = validateStringNotEmpty(scanner.nextLine(), "Surname cannot be empty!");

        System.out.println(GREEN + "Enter your phone number (ex: 555-123-4567):" + RESET);
        String telefonNo = validatePhone(scanner.nextLine());

        System.out.println(GREEN + "Enter city information:" + RESET);
        String city = validateStringNotEmpty(scanner.nextLine(), "City information cannot be empty!");

        System.out.println(GREEN + "Enter country information:" + RESET);
        String country = validateStringNotEmpty(scanner.nextLine(), "Country information cannot be empty!");

        System.out.println(GREEN + "Enter street information:" + RESET);
        String street = validateStringNotEmpty(scanner.nextLine(), "Street information cannot be empty!");

        System.out.println(GREEN + "Enter postal code:" + RESET);
        String zipcode = validateStringNotEmpty(scanner.nextLine(), "Postcode cannot be empty!");

        Address address = new Address(city, country, street, zipcode);
        addressList.add(address);

        User user = new User(customerList.size() + 1, name, lastname, telefonNo, email, password, address);
        customerList.add(user);

        System.out.println(CYAN + "Dear " + name + ", Your registration has been completed successfully." + RESET);
        System.out.println(CYAN + "You can use your email and password to log in to the system." + RESET);

    }
}
