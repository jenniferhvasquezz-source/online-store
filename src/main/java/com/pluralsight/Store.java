
package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Starter code for the Online Store workshop.
 * Learners will complete the TODO sections to make the program work.
 * <p>
 * What is already done for you: the two lists, the Home Screen loop and
 * the method headers. Each TODO says what that method has to do.
 * Product.java is complete; products.csv sits at the project root.
 * You will need extra imports (for example java.io.BufferedReader,
 * java.io.FileReader, java.io.IOException) as you fill the TODOs in.
 */
public class Store {

    public static void main(String[] args) {

        // Create lists for inventory and the shopping cart
        ArrayList<Product> inventory = new ArrayList<>();
        ArrayList<Product> cart = new ArrayList<>();

        // Load inventory from the data file (pipe-delimited: SKU|Product Name|Price|Department)
        loadInventory("products.csv", inventory);

        // Main menu loop
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 3) {
            System.out.println("\nWelcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter 1, 2, or 3.");
                scanner.nextLine();                 // discard bad input
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();                     // clear newline

            switch (choice) {
                case 1 -> displayProducts(inventory, cart, scanner);
                case 2 -> displayCart(cart, scanner);
                case 3 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    /**
     * Reads product data from a file and populates the inventory list.
     * File format (pipe-delimited), the first line is a header row:
     * SKU|Product Name|Price|Department
     * <p>
     * Example line:
     * AV1051|JBL Bluetooth Speaker|89.95|Audio Video
     */
    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        // TODO: skip the header line, then read each line, split on "|",
        //       create a Product object, and add it to the inventory list
        public static void loadInventory(String fileName, ArrayList<Product> inventory) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line = reader.readLine(); // Skip header row

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 4) {
                        String sku = parts[0];
                        String name = parts[1];
                        double price = Double.parseDouble(parts[2]);
                        String department = parts[3];

                        Product product = new Product(sku, name, price, department);
                        inventory.add(product);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading inventory file: " + e.getMessage());
            }
        }
    }

    /**
     * Displays all products and lets the user add one to the cart.
     * Typing X returns to the main menu.
     */
    public static void displayProducts(ArrayList<Product> inventory,
                                       ArrayList<Product> cart,
                                       Scanner scanner) {
        // TODO: show each product (id, name, price),
        //       prompt for an id, find that product, add to cart
        public static void displayCart(ArrayList<Product> cart, Scanner scanner) {
            if (cart.isEmpty()) {
                System.out.println("\nYour cart is currently empty.");
                return;
            }

            System.out.println("\n--- Your Shopping Cart ---");
            double totalAmount = 0.0;
            for (Product p : cart) {
                System.out.printf("%s - %s: $%.2f\n", p.getSku(), p.getName(), p.getPrice());
                totalAmount += p.getPrice();
            }
            System.out.printf("Total: $%.2f\n", totalAmount);

            System.out.print("\nWould you like to check out (C) or return to main menu (X)? ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("C")) {
                checkOut(cart, totalAmount, scanner);
            }
        }
    }

    /**
     * Shows the contents of the cart, calculates the total,
     * and offers the option to check out.
     */
    public static void displayCart(ArrayList<Product> cart, Scanner scanner) {
        // TODO:
        //   • list each product in the cart
        //   • compute the total cost
        //   • ask the user whether to check out (C) or return (X)
        //   • if C, call checkOut(cart, totalAmount, scanner)
        public static void checkOut(ArrayList<Product> cart,
        double totalAmount,
        Scanner scanner) {
            System.out.println("\n--- Checkout ---");
            System.out.printf("Your total is: $%.2f\n", totalAmount);
            System.out.print("Confirm purchase? (Y/N): ");
            String confirm = scanner.nextLine().trim();

            if (!confirm.equalsIgnoreCase("Y")) {
                System.out.println("Checkout canceled.");
                return;
            }

            System.out.print("Enter payment amount ($): ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid amount entered. Checkout failed.");
                scanner.nextLine(); // clear buffer
                return;
            }
            double payment = scanner.nextDouble();
            scanner.nextLine(); // clear newline

            if (payment < totalAmount) {
                System.out.println("Insufficient funds! Checkout failed.");
                return;
            }

            double change = payment - totalAmount;

            // Display receipt
            System.out.println("\n========= RECEIPT =========");
            for (Product p : cart) {
                System.out.printf("- %s: $%.2f\n", p.getName(), p.getPrice());
            }
            System.out.printf("Total:   $%.2f\n", totalAmount);
            System.out.printf("Payment: $%.2f\n", payment);
            System.out.printf("Change:  $%.2f\n", change);
            System.out.println("===========================");
            System.out.println("Thank you for your purchase!");

            // Clear cart
            cart.clear();
        }
    }

    /**
     * Handles the checkout process:
     * 1. Confirm that the user wants to buy.
     * 2. Accept payment and calculate change.
     * 3. Display a simple receipt.
     * 4. Clear the cart.
     */
    public static void checkOut(ArrayList<Product> cart,
                                double totalAmount,
                                Scanner scanner) {
        // TODO: implement steps listed above
    }

    /**
     * Searches a list for a product by its id.
     *
     * @return the matching Product, or null if not found
     */
    public static Product findProductById(String id, ArrayList<Product> inventory) {
        // TODO: loop over the list and compare ids
        return null;
        public static Product findProductById(String id, ArrayList<Product> inventory) {
            for (Product p : inventory) {
                if (p.getSku().equalsIgnoreCase(id)) {
                    return p;
                }
            }
            return null;
        }
    }
}

package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

 */
public class Store {

    public static void main(String[] args) {

        // Create lists for inventory and the shopping cart
        ArrayList<Product> inventory = new ArrayList<>();
        ArrayList<Product> cart = new ArrayList<>();

        // Load inventory from the data file (pipe-delimited: SKU|Product Name|Price|Department)
        loadInventory("products.csv", inventory);

        // Main menu loop
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 3) {
            System.out.println("\nWelcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter 1, 2, or 3.");
                scanner.nextLine();                 // discard bad input
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();                     // clear newline

            switch (choice) {
                case 1 -> displayProducts(inventory, cart, scanner);
                case 2 -> displayCart(cart, scanner);
                case 3 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    /**
     * Reads product data from a file and populates the inventory list.
     * File format (pipe-delimited), the first line is a header row:
     * SKU|Product Name|Price|Department
     * <p>
     * Example line:
     * AV1051|JBL Bluetooth Speaker|89.95|Audio Video
     */
    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // Skip header row

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String sku = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String department = parts[3];

                    Product product = new Product(sku, name, price, department);
                    inventory.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
    }

    /**
     * Displays all products and lets the user add one to the cart.
     * Typing X returns to the main menu.
     */
    public static void displayProducts(ArrayList<Product> inventory,
                                       ArrayList<Product> cart,
                                       Scanner scanner) {
        boolean backToMenu = false;
        while (!backToMenu) {
            System.out.println("\n--- Product Inventory ---");
            for (Product p : inventory) {
                System.out.printf("ID: %s | Name: %s | Price: $%.2f | Dept: %s\n",
                        p.getSku(), p.getName(), p.getPrice(), p.getDepartment());
            }

            System.out.print("\nEnter Product ID to add to cart (or type 'X' to return): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("X")) {
                backToMenu = true;
            } else {
                Product found = findProductById(input, inventory);
                if (found != null) {
                    cart.add(found);
                    System.out.println("Successfully added " + found.getName() + " to your cart!");
                } else {
                    System.out.println("Product ID not found. Please try again.");
                }
            }
        }
    }

    /**
     * Shows the contents of the cart, calculates the total,
     * and offers the option to check out.
     */
    public static void displayCart(ArrayList<Product> cart, Scanner scanner) {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is currently empty.");
            return;
        }

        System.out.println("\n--- Your Shopping Cart ---");
        double totalAmount = 0.0;
        for (Product p : cart) {
            System.out.printf("%s - %s: $%.2f\n", p.getSku(), p.getName(), p.getPrice());
            totalAmount += p.getPrice();
        }
        System.out.printf("Total: $%.2f\n", totalAmount);

        System.out.print("\nWould you like to check out (C) or return to main menu (X)? ");
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("C")) {
            checkOut(cart, totalAmount, scanner);
        }
    }

    /**
     * Handles the checkout process:
     * 1. Confirm that the user wants to buy.
     * 2. Accept payment and calculate change.
     * 3. Display a simple receipt.
     * 4. Clear the cart.
     */
    public static void checkOut(ArrayList<Product> cart,
                                double totalAmount,
                                Scanner scanner) {
        System.out.println("\n--- Checkout ---");
        System.out.printf("Your total is: $%.2f\n", totalAmount);
        System.out.print("Confirm purchase? (Y/N): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Checkout canceled.");
            return;
        }

        System.out.print("Enter payment amount ($): ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount entered. Checkout failed.");
            scanner.nextLine(); // clear buffer
            return;
        }
        double payment = scanner.nextDouble();
        scanner.nextLine(); // clear newline

        if (payment < totalAmount) {
            System.out.println("Insufficient funds! Checkout failed.");
            return;
        }

        double change = payment - totalAmount;

        // Display receipt
        System.out.println("\n========= RECEIPT =========");
        for (Product p : cart) {
            System.out.printf("- %s: $%.2f\n", p.getName(), p.getPrice());
        }
        System.out.printf("Total:   $%.2f\n", totalAmount);
        System.out.printf("Payment: $%.2f\n", payment);
        System.out.printf("Change:  $%.2f\n", change);
        System.out.println("===========================");
        System.out.println("Thank you for your purchase!");

        // Clear cart
        cart.clear();
    }

    /**
     * Searches a list for a product by its id.
     *
     * @return the matching Product, or null if not found
     */
    public static Product findProductById(String id, ArrayList<Product> inventory) {
        for (Product p : inventory) {
            if (p.getSku().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
}


