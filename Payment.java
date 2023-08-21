package Project;

import java.util.Scanner;

public class Payment implements Systems {
    public static Scanner in = new Scanner(System.in);
    private int PaymentID;
    private double Amount;
    private String PaymentMethod;

    public Payment() {

    }

    public Payment(int paymentID, double amount, String paymentMethod) {
        PaymentID = paymentID;
        Amount = amount;
        PaymentMethod = paymentMethod;
    }

    public Payment(int PaymentID) {
        this.PaymentID = PaymentID;
    }

    public int getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(int paymentID) {
        PaymentID = paymentID;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public int orderID() {
        int i = 0;
        return i;
    }

    public String toString() {
        return "PaymentID: " + PaymentID + "\n" +
                "Amount: " + Amount + "\n" +
                "PaymentMethod: " + PaymentMethod + "\n" + "OrderID: " + orderID();
    }
}