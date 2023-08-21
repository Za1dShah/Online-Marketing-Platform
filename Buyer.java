package Project;

import java.util.*;

class Buyer extends User implements CartOperations {
    static Scanner in = new Scanner(System.in);
    static private ArrayList<Product> cart;

    public Buyer(String username, String password, String email) {
        super(username, password, email);
        cart = new ArrayList<>();
    }

    public static ArrayList<Product> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }

    public static void removeProductFromCartMenu(Buyer buyer) {
        System.out.println("------ Remove Product from Cart ------");
        ArrayList<Product> cart = buyer.getCart();

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to remove.");
        } else {
            System.out.println("Products in your cart:");
            for (int i = 0; i < cart.size(); i++) {
                Product product = cart.get(i);
                System.out.println((i + 3) + ". " + product.getName());
            }

            System.out.print("Enter the name of the product to remove: ");
            String productName = in.nextLine();

            buyer.removeFromCart(productName);
        }

    }

    @Override
    public void removeFromCart(String productName) {
        Product productToRemove = null;
        for (Product product : cart) {
            if (product.getName().equalsIgnoreCase(productName)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            cart.remove(productToRemove);
            System.out.println("Product removed from cart successfully!");
        } else {
            System.out.println("Product not found in cart.");
        }

    }

    @Override
    public void addToCart(Product product) {
        cart.add(product);
    }
}