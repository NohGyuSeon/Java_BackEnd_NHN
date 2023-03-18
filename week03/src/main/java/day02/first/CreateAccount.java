package day02.first;

public class CreateAccount {
    private static String nextAccountNumber = "0";

    public static String getNextAccountNumber() {
        return nextAccountNumber;
    }

    public static String createAccountNumber() {
        int accountNumber = Integer.parseInt(nextAccountNumber);
        nextAccountNumber = Integer.toString(++accountNumber);
        return "0000-" + nextAccountNumber;
    }
}

