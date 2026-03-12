public class InvalidAccountException extends RuntimeException {
    
    private int accountNumber;
    
    public InvalidAccountException(int accountNumber) {
        super("Account not found: " + accountNumber);
        this.accountNumber = accountNumber;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public void displayErrorDetails() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        ACCOUNT NOT FOUND ERROR         ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("\nThis account does not exist in our system.");
        System.out.println("Please verify the account number and try again.");
    }
}
