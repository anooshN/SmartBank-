public class InvalidAmountException extends RuntimeException {
    
    private double amount;
    private String operation;
    
    public InvalidAmountException(double amount, String operation) {
        super("Invalid amount for " + operation + ": $" + amount);
        this.amount = amount;
        this.operation = operation;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void displayErrorDetails() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        INVALID AMOUNT ERROR            ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Operation: " + operation);
        System.out.printf("Amount: $%,.2f%n", amount);
        
        if (amount <= 0) {
            System.out.println("\nAmount must be positive!");
        } else if (amount > 1000000) {
            System.out.println("\nAmount exceeds maximum allowed ($1,000,000)!");
        }
        
        System.out.println("Please enter a valid amount.");
    }
}