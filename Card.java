package Project;

public class Card extends Payment {
    private long cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private int cardCVV;

    public Card() {

    }

    public Card(int paymentID, double amount, long cardNumber, String cardHolderName, String cardExpirationDate,
            int cardCVV) {
        super(paymentID, amount, "Card");
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpirationDate = cardExpirationDate;
        this.cardCVV = cardCVV;
    }

    // Getters and setters for card details

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) throws MyException {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public int getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(int cardCVV) {
        this.cardCVV = cardCVV;
    }

    public static boolean processCardDetails() throws MyException {

        System.out.println("Enter card details:");
        System.out.print("Card Number: ");
        String cardNumber = in.nextLine();

        // Validate card number
        if (!isValidCardNumber(cardNumber)) {
            throw new MyException(5);
        }

        System.out.print("Expiry Date (MM/YY): ");
        String expiryDate = in.nextLine();

        // Validate expiry date
        if (!isValidExpiryDate(expiryDate)) {
            throw new MyException(4);
        }

        System.out.print("CVV: ");
        String cvv = in.nextLine();

        // Validate CVV
        if (!isValidCVV(cvv)) {
            throw new MyException(3);
        }

        System.out.println("Card details processed successfully!");
        return true;
    }

    // Method to validate card number
    private static boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{16}"); // Validate 16-digit card number
    }

    // Method to validate expiry date
    private static boolean isValidExpiryDate(String expiryDate) {
        return expiryDate.matches("\\d{2}/\\d{2}"); // Validate MM/YY format
    }

    // Method to validate CVV
    private static boolean isValidCVV(String cvv) {
        // Implement CVV validation logic
        return cvv.matches("\\d{3}"); // Validate 3-digit CVV
    }
}
