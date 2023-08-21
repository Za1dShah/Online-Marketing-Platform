package Project;

public class MyException extends Exception {
    private int errorCode;

    MyException(int errorCode) {
        this.errorCode = errorCode;
    }

    public String toString() {
        String message = "";
        if (errorCode == 1) {
            message = "Invalid User ID or Password";
        } else if (errorCode == 2) {
            message = "Invalid choice";
        } else if (errorCode == 3) {
            message = "Invalid CVV";
        } else if (errorCode == 4) {
            message = "Card Expired";
        } else if (errorCode == 5) {
            message = "Invalid Card Number";
        }

        return message;
    }
}
