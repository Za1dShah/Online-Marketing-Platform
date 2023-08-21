package Project;

public class COD extends Payment {
    public COD(int paymentID, double amount) {
        super(paymentID, amount, "Cash On Delivery");
    }

    public static void processCashPayment() {
        System.out.println("Payment received in cash.");
    }
}