package Project;

import java.io.*;
import java.util.*;

public class Driver {
    static Scanner scanner = new Scanner(System.in);
    static final String ACCOUNTS_FILE = "accounts.txt";
    static List<Product> products = new ArrayList<>();
    static ArrayList<Product> productList = new ArrayList<>();
    static ArrayList<Product> cart = new ArrayList<>();

    public static void main(String[] args) throws MyException {
        productList.addAll(Product.getMenProducts());
        productList.addAll(Product.getWomenProducts());
        productList.addAll(Product.getHomeProducts());
        productList.addAll(Product.getBeautyProducts());

        while (true) {
            int choice;
            // Displaying sign in & sign up.
            System.out.println("Welcome to the Online Marketing System");
            System.out.println("1. Sign In" + "\n2. Sign Up" + "\n3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                User user = signInMenu();
                if (user instanceof Buyer) {
                    System.out.println("Welcome Buyer!");
                    Buyer buyer = (Buyer) user;
                    buyerMenu(buyer);
                } else if (user instanceof Seller) {
                    System.out.println("Welcome Seller!");
                    Seller seller = (Seller) user;
                    sellerMenu(seller);
                }
            } else if (choice == 2) {
                User user = signUpMenu();
                if (user instanceof Buyer) {
                    System.out.println("Welcome Buyer!");
                    Buyer buyer = (Buyer) user;
                    buyerMenu(buyer);
                } else if (user instanceof Seller) {
                    System.out.println("Welcome Seller!");
                    Seller seller = (Seller) user;
                    sellerMenu(seller);
                }
            } else if (choice == 3) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            // ClearScreen();
        }
    }

    // Clears console when the method is called
    public static void ClearScreen() {
        // [H it moves the cursor to the top left side of the
        // [2J clears the console from the cursor to the end of the screen.
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Sign in menu
    public static User signInMenu() throws MyException {
        // ClearScreen();
        System.out.println("------ Sign In ------");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Check if username and password are in the accounts file
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(",");
                String savedUsername = accountData[0],
                        savedPassword = accountData[1],
                        savedEmail = accountData[2],
                        userType = accountData[3];

                if (savedUsername.equals(username) && savedPassword.equals(password)) {
                    System.out.println("Sign in successful!");
                    // it will create instance of buyer and seller
                    if (userType.equals("Buyer")) {
                        return new Buyer(savedUsername, savedPassword, savedEmail);
                    } else if (userType.equals("Seller")) {
                        return new Seller(savedUsername, savedPassword, savedEmail);
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("Error reading accounts file: " + e.getMessage());
        }

        System.out.println("Invalid username or password. Please try again.");
        // Return null if sign-in fails
        return null;
    }

    public static User signUpMenu() throws MyException {
        ClearScreen();
        System.out.println("------ Sign Up ------");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Creating the user object based on the user type
        System.out.println("Select User Type:");
        System.out.println("1. Buyer" + "\n2. Seller");
        System.out.print("Enter your choice: ");
        int userTypeChoice = scanner.nextInt();
        scanner.nextLine();

        User user = null;
        String userType = "";

        if (userTypeChoice == 1) {
            user = new Buyer(username, password, email);
            userType = "Buyer";
        } else if (userTypeChoice == 2) {
            user = new Seller(username, password, email);
            userType = "Seller";
        } else {
            throw new MyException(2);
        }

        // Save the account to the accounts file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE, true))) {
            String accountData = username + "," + password + "," + email + "," + userType;
            writer.write(accountData);
            writer.newLine();
            System.out.println("Sign up successful!");
        } catch (IOException e) {
            System.out.println("Error writing to accounts file: " + e.getMessage());
        }

        return user;
    }

    // Products menu
    public static void viewProductsMenu(Buyer buyer) {
        ClearScreen();
        int categoryChoice;
        System.out.println("------ View Products ------");
        System.out.println("1. Men\n2. Women\n3. Home\n4. Beauty");
        System.out.print("Enter category choice: ");
        categoryChoice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Product> products = null;
        String category = "";

        switch (categoryChoice) {
            case 1:
                products = Product.getMenProducts();
                category = "Men";
                break;
            case 2:
                products = Product.getWomenProducts();
                category = "Women";
                break;
            case 3:
                products = Product.getHomeProducts();
                category = "Home";
                break;
            case 4:
                products = Product.getBeautyProducts();
                category = "Beauty";
                break;
            default:
                System.out.println("Invalid category choice.");
                break;
        }

        if (products.isEmpty()) {
            System.out.println("No products available in the selected category.");
        } else {
            System.out.println("Available products in the " + category + " category:");
            System.out.println(products);
        }
    }

    // Products menu for seller
    public static void viewProductsMenu(Seller seller) {
        ClearScreen();
        System.out.println("------ View Products ------");

        // Display available products
        System.out.println("Available products:");
        for (Product product : productList) {
            System.out.println(product);
            System.out.println();
        }
    }

    // Getting the product by its id ????
    public static Product getProductById(int productId) {
        for (Product product : productList) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    // Generating a unique id when adding products

    public static int generateProductId() {
        // Generate a unique product ID based on the current products in the list
        int maxId = 0;
        for (Product product : productList) {
            int productId = product.getProductId();
            if (productId > maxId) {
                maxId = productId;
            }
        }
        return maxId + 1;
    }

    // Buyer Menu
    public static void buyerMenu(Buyer buyer) throws MyException {
        ClearScreen();
        int choice;

        while (true) {
            System.out.println("\n------ Buyer Menu ------");
            System.out.println("1. View Products" + "\n2. Add Product to Cart" +
                    "\n3. View Cart" + "\n4. Checkout" + "\n5. Remove product from cart" + "\n6. Sign Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewProductsMenu(buyer);
                    break;
                case 2:
                    addProductToCartMenu(buyer);
                    break;
                case 3:
                    viewCartMenu(buyer);
                    break;
                case 4:
                    checkoutMenu(buyer);
                    break;
                case 5:
                    Buyer.removeProductFromCartMenu(buyer);
                    break;
                case 6:
                    System.out.println("Signed out successfully!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add Product to Cart Menu
    public static void addProductToCartMenu(Buyer buyer) {
        System.out.println("------ Add Product to Cart ------");
        // Display available products
        System.out.println("Available products:");
        for (Product product : productList) {
            System.out.println(product);
            System.out.println();
        }

        System.out.print("Enter the product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        // Retrieve the product details based on the product ID
        Product product = getProductById(productId);

        if (product != null) {
            buyer.addToCart(product);
            System.out.println("Product added to cart successfully!");
        } else {
            System.out.println("Invalid product ID. Please try again.");
        }
    }

    public static Product getProductById(int productId, ArrayList<Product> products) {
        for (Product product : productList) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    // View Cart Menu
    public static void viewCartMenu(Buyer buyer) {
        ClearScreen();
        cart = Buyer.getCart();
        System.out.println("------ View Cart ------");

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Products in your cart:");
            for (Product product : cart) {
                System.out.println(product.toString());
                System.out.println();
            }
        }
    }

    // checkoutmenu
    public static void checkoutMenu(Buyer buyer) throws MyException {
        ClearScreen();
        System.out.println("------ Checkout ------");
        List<Product> cart = buyer.getCart();

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
        } else {
            double total = 0;

            System.out.println("Products in your cart:");
            for (Product product : cart) {
                System.out.println(product.toString());
                total += product.getPrice();
            }

            System.out.println("Total: $" + total);

            // Prompt the user to choose the payment method
            System.out.println("Please choose a payment method:");
            System.out.println("1. Card");
            System.out.println("2. Cash");

            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                // Pay by card
                Card card = new Card();
                boolean paymentSuccess = card.processCardDetails();
                if (paymentSuccess) {
                    System.out.println("Checkout successful! Payment received via card. Thank you for shopping.");

                    // Generate order invoice and save it to a file
                    OrderInvoice orderInvoice = new OrderInvoice(OrderInvoice.generateOrderId(),
                            OrderInvoice.generatePaymentId(),
                            OrderInvoice.getCurrentDate(),
                            new ArrayList<>(cart), total, buyer.getUsername());
                    orderInvoice.writeInvoiceToFile();
                    buyer.clearCart();
                } else {
                    System.out.println("Payment failed. Please try again.");
                }
            } else if (choice == 2) {
                // Pay by cash
                System.out.println("Checkout successful! Thank you for shopping.");

                // Generate order invoice and save it to a file
                OrderInvoice orderInvoice = new OrderInvoice(OrderInvoice.generateOrderId(),
                        OrderInvoice.generatePaymentId(),
                        OrderInvoice.getCurrentDate(),
                        new ArrayList<>(cart), total, buyer.getUsername());
                orderInvoice.writeInvoiceToFile();
                buyer.clearCart();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Seller Menu
    public static void sellerMenu(Seller seller) {
        ClearScreen();
        int choice;

        while (true) {
            System.out.println("\n------ Seller Menu ------");
            System.out.println("1. Add Product" + "\n2. Remove Product" +
                    "\n3. View Products" + "\n4. Sign Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProductMenu(seller);
                    break;
                case 2:
                    removeProductMenu(seller);
                    break;
                case 3:
                    viewProductsMenu(seller);
                    break;
                case 4:
                    System.out.println("Signed out successfully!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // AddProduct Menu
    public static void addProductMenu(Seller seller) {
        ClearScreen();
        System.out.println("------ Add Product ------");
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        // Generate a unique product ID
        int productId = generateProductId();

        // Create a new product and add it to the list
        Product product = new Product(productId, name, price, description);
        productList.add(product); // Add the product to productList ArrayList

        System.out.println("Product added successfully!");
    }

    public static void removeProductMenu(Seller seller) {
        System.out.println("------ Remove Product ------");
        System.out.print("Enter the product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        boolean removed = removeProductById(productId);

        if (removed) {
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Invalid product ID. Please try again.");
        }
    }

    public static boolean removeProductById(int productId) {
        for (Product product : productList) { // Iterate over productList ArrayList
            if (product.getProductId() == productId) {
                productList.remove(product); // Remove the product from productList
                return true;
            }
        }
        return false;
    }
}