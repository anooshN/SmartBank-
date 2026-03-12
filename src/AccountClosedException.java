public class AccountClosedException extends RuntimeException {
    
    private int accountNumber;
    private String operation;
    
    public AccountClosedException(int accountNumber, String operation) {
        super("Cannot perform " + operation + " - Account " + accountNumber + " is closed!");
        this.accountNumber = accountNumber;
        this.operation = operation;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void displayErrorDetails() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       ACCOUNT CLOSED ERROR             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Attempted Operation: " + operation);
        System.out.println("\nThis account has been closed and cannot be used.");
        System.out.println("Please contact customer service to reactivate.");
    }
}