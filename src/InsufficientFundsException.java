public class InsufficientFundsException extends RuntimeException {
    
    private double amount;
    private double balance;
    private double shortage;
    
    public InsufficientFundsException(double amount, double balance) {
        super("Insufficient funds! Attempted to withdraw $" + String.format("%,.2f", amount) + 
              " but balance is only $" + String.format("%,.2f", balance));
        
        this.amount = amount;
        this.balance = balance;
        this.shortage = amount - balance;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public double getShortage() {
        return shortage;
    }
    
    public void displayErrorDetails() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     INSUFFICIENT FUNDS ERROR           ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.printf("Withdrawal Amount: $%,.2f%n", amount);
        System.out.printf("Current Balance:   $%,.2f%n", balance);
        System.out.printf("Shortage:          $%,.2f%n", shortage);
        System.out.println("\nPlease deposit at least $" + String.format("%,.2f", shortage) + " to complete this transaction.");
    }
}