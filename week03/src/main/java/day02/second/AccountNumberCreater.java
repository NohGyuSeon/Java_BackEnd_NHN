package day02.second;

public class AccountNumberCreater {
    private static String nextAccountNumber = "0";

    public static String createAccountNumber(AccountCode accountCode) {
        int accountNumber = Integer.parseInt(nextAccountNumber);
        nextAccountNumber = Integer.toString(++accountNumber);
        return accountCode.getName() + "-" + nextAccountNumber;
    }
}

enum AccountCode {
    DEFAULT("0000"), LIMITED("0001"), SAVINGS("0002");

    private final String name; 
	public String getName() { 
		return name; 
    } 

	private AccountCode(String name){ 
		this.name = name; 
    } 
}