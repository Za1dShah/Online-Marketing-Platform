package Project;

import java.util.ArrayList;
import java.util.Scanner;

enum ProductCategory {
    MEN,
    WOMEN,
    HOME,
    BEAUTY
}

public final class Product {
    private int productId;
    private String name, description;
    private double price;
    private ProductCategory pcat;
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<Product> menproducts;
    static ArrayList<Product> womenproducts;
    static ArrayList<Product> homeproducts;
    static ArrayList<Product> beautyproducts;

    static Scanner scanner = new Scanner(System.in);

    public Product() {
    }

    public Product(int productId, String name, double price, String description) {
        menproducts = new ArrayList<>();
        womenproducts = new ArrayList<>();
        beautyproducts = new ArrayList<>();
        homeproducts = new ArrayList<>();
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getPcat() {
        return pcat;
    }

    public void setPcat(ProductCategory pcat) {
        this.pcat = pcat;
    }

    public static ArrayList<Product> getMenProducts() {
        // Add Men's products
        Product shirt = new Product(1, "Shirt", 25.99, "Comfortable and stylish shirt for men");
        Product pant = new Product(2, "Pant", 39.99, "Trendy and durable pant for men");
        Product undergarment = new Product(3, "Undergarment", 12.99, "High-quality undergarments for men");

        menproducts.add(shirt);
        menproducts.add(pant);
        menproducts.add(undergarment);

        return menproducts;
    }

    public static ArrayList<Product> getWomenProducts() {
        // Add Women's products
        Product dress = new Product(4, "Dress", 49.99, "Elegant and fashionable dress for women");
        Product skirt = new Product(5, "Skirt", 34.99, "Trendy and comfortable skirt for women");
        Product socks = new Product(6, "Socks", 29.99, "Stylish and versatile socks for women");

        womenproducts.add(dress);
        womenproducts.add(skirt);
        womenproducts.add(socks);

        return womenproducts;
    }

    public static ArrayList<Product> getHomeProducts() {
        // Add Home products
        Product bedsheet = new Product(7, "Bedsheet", 19.99, "Soft and cozy bedsheet for your bedroom");
        Product curtains = new Product(8, "Curtains", 24.99, "Beautiful curtains to enhance your home decor");
        Product cushion = new Product(9, "Cushion", 9.99, "Comfortable cushions for your living room");

        homeproducts.add(bedsheet);
        homeproducts.add(curtains);
        homeproducts.add(cushion);

        return homeproducts;
    }

    public static ArrayList<Product> getBeautyProducts() {
        // Add Beauty products
        Product lipstick = new Product(10, "Lipstick", 12.99, "Vibrant and long-lasting lipstick");
        Product foundation = new Product(11, "Foundation", 18.99,
                "Smooth and flawless foundation for your skin");
        Product mascara = new Product(12, "Mascara", 9.99,
                "Lengthening and volumizing mascara for beautiful lashes");

        beautyproducts.add(lipstick);
        beautyproducts.add(foundation);
        beautyproducts.add(mascara);

        return beautyproducts;
    }

    public static void viewProductsMenu(Buyer buyer) {
        int categoryChoice;

        System.out.println("------ View Products ------");
        System.out.println("1. Men\n2. Women\n3. Home\n4. Beauty");
        System.out.print("Enter category choice: ");
        categoryChoice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Product> products = getProductsByCategory(ProductCategory.values()[categoryChoice - 1]);

        if (products != null) {
            System.out.println("Available products in the selected category:");
            for (Product product : products) {
                System.out.println(product.toString());
            }
        }
    }

    public static ArrayList<Product> getProductsByCategory(ProductCategory categoryChoice) {
        ArrayList<Product> productsByCategory = new ArrayList<>();

        for (Product product : products) {
            if (product.getPcat() == categoryChoice) {
                productsByCategory.add(product);
            }
        }

        return productsByCategory;
    }

    public static Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }

        return null;
    }

    public String toString() {
        return "Product ID: " + productId + "\nProduct Name: " + name + "\nPrice: " + price + "\nDescription: "
                + description;
    }
}