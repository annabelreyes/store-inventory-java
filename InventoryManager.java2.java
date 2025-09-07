import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class InventoryManager {
    static ArrayList<String> productIDs = new ArrayList<>();
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<String> descriptions = new ArrayList<>();
    static ArrayList<Double> prices = new ArrayList<>();
    static ArrayList<Integer> quantities = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
    static NumberFormat integerFormat = NumberFormat.getIntegerInstance();
    public static void main(String[] args) {
        loadTestData();
        int choice;
        do {
            System.out.println("\nINVENTORY MENU:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Update Product");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            choice = getPositiveInteger(sc,"");
            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    System.out.println("Exiting Inventory Manager.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);
    }
    public static void viewProducts() {
        System.out.println("\nCurrent Inventory:");
        System.out.printf("%-10s %-10s %-40s %-10s %-10s\n", "ProductID", "Name", "Description", "Price", "Quantity");
        for (int i = 0; i < productIDs.size(); i++) {
            System.out.printf("%-10s %-10s %-40s %-10s %-10s\n",
                    productIDs.get(i),
                    names.get(i),
                    descriptions.get(i),
                    currency.format(prices.get(i)),
                    integerFormat.format(quantities.get(i)));
        }
    }
    public static void addProduct() {
        String productID;
        do {
            productID = getStringBetween(sc, "Enter unique Product ID (5-10 chars): ", 5, 10);
            if (productIDs.contains(productID.toLowerCase())) {
                System.out.println("Product ID already exists. Try another.");
            }
        } while (productIDs.contains(productID.toLowerCase()));
        String name = getStringBetween(sc, "Enter product name(<10 chars): ", 1, 9);
        String description = getStringBetween(sc, "Enter product description (<40 chars): ", 1, 39);
        double price = getPositiveDouble(sc, "Enter product price: ");
        int quantity = getPositiveInteger(sc, "Enter product quantity: ");
        productIDs.add(productID.toLowerCase());
        names.add(name);
        descriptions.add(description);
        prices.add(price);
        quantities.add(quantity);
        System.out.println("Product added successfully!");
    }
    public static void removeProduct() {
        String productID = getStringBetween(sc, "Enter the Product ID to remove: ", 5, 10).toLowerCase();
        int index = productIDs.indexOf(productID);
        if (index != -1) {
            productIDs.remove(index);
            names.remove(index);
            descriptions.remove(index);
            prices.remove(index);
            quantities.remove(index);
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Product ID not found.");
        }
    }
    public static void updateProduct() {
        String productID = getStringBetween(sc, "Enter the product ID to update: ", 5, 10).toLowerCase();
        int index = productIDs.indexOf(productID);
        if (index != -1) {
            double newPrice = getPositiveDouble(sc, "Enter new price: ");
            int newQty = getPositiveInteger(sc, "Enter new Quantity: ");
            prices.set(index, newPrice);
            quantities.set(index, newQty);
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product ID not found.");
        }
    }
    public static void loadTestData() {
        productIDs.add("lap17");
        names.add("Laptop");
        descriptions.add("17-inch high-performance laptop");
        prices.add(1299.99);
        quantities.add(10);
        productIDs.add("phn12");
        names.add("Phone");
        descriptions.add("5G smartphone with OLED display");
        prices.add(899.50);
        quantities.add(25);
        productIDs.add("tab10");
        names.add("Tablet");
        descriptions.add("10-inch tablet with stylus");
        prices.add(499.99);
        quantities.add(15);
    }
    public static String getStringBetween(Scanner sc, String prompt, int min, int max) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.length() >= min && input.length() <= max) {
                return input;
            } else {
                System.out.printf("Input must be between %d and %d characters. \n", min, max);
            }
        }
    }
    public static int getPositiveInteger(Scanner sc, String prompt) {
        int value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value <= 0) {
                    System.out.println("Value must be positive.");
                }
            } else {
                System.out.println("Invalid integer. Please enter a whole number.");
                sc.next(); // clear invalid input
            }
        }
        sc.nextLine(); // clear the leftover newline
        return value;
    }
    public static double getPositiveDouble(Scanner sc, String prompt) {
        double value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                value = sc.nextDouble();
                if (value <= 0) {
                    System.out.println("Value must be positive.");
                }
            } else {
                System.out.println("Invalid number. Please enter a decimal number.");
                sc.next(); // clear invalid input
            }
        }
        sc.nextLine(); // clear the leftover newLine
        return value;
    }
}