package Project;

import java.io.*;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class OrderInvoice extends Payment {
    static private int OrderID, invoiceNumber = 1;
    static private String OrderDate, username;
    static ArrayList<Product> Items = new ArrayList<>();
    static private double TotalPrice;

    public OrderInvoice() {
    }

    public OrderInvoice(int OrderID, int PaymentID, String OrderDate, ArrayList<Product> Items, double TotalPrice,
            String username) {
        super(PaymentID);
        OrderInvoice.OrderID = OrderID;
        OrderInvoice.OrderDate = OrderDate;
        OrderInvoice.Items = Items;
        OrderInvoice.TotalPrice = TotalPrice;
        OrderInvoice.username = username;
        OrderInvoice.OrderID = invoiceNumber++;
    }

    public ArrayList<Product> getItems() {
        return Items;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public int getOrderID() {
        return OrderID;
    }

    public static double getTotalPrice() {
        return TotalPrice;
    }

    public String getUsername() {
        return username;
    }

    public static void setItems(ArrayList<Product> items) {
        Items = items;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public void setUsername(String username) {
        OrderInvoice.username = username;
    }

    public void writeInvoiceToFile() {
        String fileName = "invoice_" + OrderID + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Order ID: " + OrderID);
            writer.newLine();
            writer.write("Order Date: " + OrderDate);
            writer.newLine();
            writer.write("Username: " + username);
            writer.newLine();
            writer.newLine();
            writer.write("Items:");
            writer.newLine();

            for (Product item : Items) {
                writer.write(item.toString());
                writer.newLine();
            }

            writer.newLine();
            writer.write("Total Price: $" + TotalPrice);

            System.out.println("Order invoice written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing order invoice to file: " + e.getMessage());
        }
    }

    public static int generateOrderId() {
        // Generate a random order ID within a specific range
        int minOrderId = 1000, maxOrderId = 9999;

        return ThreadLocalRandom.current().nextInt(minOrderId, maxOrderId + 1);
    }

    public static int generatePaymentId() {
        // Generate a random payment ID within a specific range
        int minPaymentId = 10000, maxPaymentId = 99999;

        return ThreadLocalRandom.current().nextInt(minPaymentId, maxPaymentId + 1);
    }

    public static String getCurrentDate() {
        // Get the current date in the format "yyyy-MM-dd"
        LocalDate currentDate = LocalDate.now();

        return currentDate.toString();
    }

    public String toString() {
        return "Order ID: " + OrderID +
                "Payment ID: " + super.getPaymentID() +
                "Order Date: " + OrderDate +
                "Item: " + Items +
                "Total Price: " + TotalPrice +
                "User Name" + username;
    }
}