import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SmartBankSystem {
    
    // Data storage
    private static HashMap<Integer, Account> accounts = new HashMap<>();
    private static HashMap<Integer, Customer> customers = new HashMap<>();
    private static ArrayList<Loan> loans = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    // File names
    private static final String CUSTOMERS_FILE = "customers.txt";
    private static final String ACCOUNTS_FILE = "accounts.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";
    private static final String LOANS_FILE = "loans.txt";
    
    // ID generators
    private static int nextAccountNumber = 5001;
    private static int nextCustomerId = 1001;
    
    public static void main(String[] args) {
        
        // Load data on startup
        loadAllData();
        
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║         SMARTBANK BANKING SYSTEM               ║");
        System.out.println("║    Complete Console Banking Application        ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            int choice = getIntInput("\nEnter your choice: ");
            
            switch (choice) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    transferMoney();
                    break;
                case 5:
                    viewAccountDetails();
                    break;
                case 6:
                    viewTransactionHistory();
                    break;
                case 7:
                    requestLoan();
                    break;
                case 8:
                    payLoan();
                    break;
                case 9:
                    applyInterest();
                    break;
                case 10:
                    generateAccountStatement();
                    break;
                case 11:
                    viewAllAccounts();
                    break;
                case 12:
                    searchAccounts();
                    break;
                case 13:
                    manageLoan();
                    break;
                case 14:
                    systemStatistics();
                    break;
                case 15:
                    saveAllData();
                    break;
                case 16:
                    saveAllData();  // Auto-save on exit
                    System.out.println("\n╔════════════════════════════════════════╗");
                    System.out.println("║   Thank you for using SmartBank!       ║");
                    System.out.println("║   Data saved. Have a great day!        ║");
                    System.out.println("╚════════════════════════════════════════╝\n");
                    running = false;
                    break;
                default:
                    System.out.println("✗ Invalid choice! Please select 1-16.");
            }
        }
        
        scanner.close();
    }
    
    // Display main menu
    private static void displayMainMenu() {
        System.out.println("\n╔═══════════════════ MAIN MENU ══════════════════════╗");
        System.out.println("║              CUSTOMER SERVICES                     ║");
        System.out.println("║  1.  Create New Account                            ║");
        System.out.println("║  2.  Deposit Money                                 ║");
        System.out.println("║  3.  Withdraw Money                                ║");
        System.out.println("║  4.  Transfer Money                                ║");
        System.out.println("║  5.  View Account Details                          ║");
        System.out.println("║  6.  View Transaction History                      ║");
        System.out.println("║  7.  Request Loan                                  ║");
        System.out.println("║  8.  Pay Loan                                      ║");
        System.out.println("║  9.  Apply Interest                                ║");
        System.out.println("║  10. Generate Account Statement                    ║");
        System.out.println("║                                                    ║");
        System.out.println("║              ADMIN SERVICES                        ║");
        System.out.println("║  11. View All Accounts                             ║");
        System.out.println("║  12. Search Accounts                               ║");
        System.out.println("║  13. Manage Loans (Approve/Reject)                 ║");
        System.out.println("║  14. System Statistics                             ║");
        System.out.println("║                                                    ║");
        System.out.println("║              SYSTEM                                ║");
        System.out.println("║  15. Save Data                                     ║");
        System.out.println("║  16. Exit                                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
    
    // 1. Create new account
    private static void createNewAccount() {
        System.out.println("\n╔══════ CREATE NEW ACCOUNT ══════╗");
        
        scanner.nextLine();  // Clear buffer
        
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("✗ Name cannot be empty!");
            return;
        }
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        
        if (!email.contains("@")) {
            System.out.println("✗ Invalid email format!");
            return;
        }
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();
        
        System.out.println("\nAccount Types:");
        System.out.println("1. SAVINGS (3.5% interest)");
        System.out.println("2. CHECKING (1.0% interest)");
        System.out.println("3. FIXED_DEPOSIT (6.5% interest)");
        int typeChoice = getIntInput("Select account type (1-3): ");
        
        String accountType;
        switch (typeChoice) {
            case 1: accountType = "SAVINGS"; break;
            case 2: accountType = "CHECKING"; break;
            case 3: accountType = "FIXED_DEPOSIT"; break;
            default:
                System.out.println("✗ Invalid choice! Defaulting to SAVINGS.");
                accountType = "SAVINGS";
        }
        
        double initialDeposit = getDoubleInput("Enter initial deposit (minimum $100): $");
        
        if (initialDeposit < 100) {
            System.out.println("✗ Minimum initial deposit is $100!");
            return;
        }
        
        // Create customer
        Customer customer = new Customer(name, nextCustomerId, email, phone);
        customers.put(nextCustomerId, customer);
        
        // Create account
        Account account = new Account(nextAccountNumber, accountType, initialDeposit, customer);
        accounts.put(nextAccountNumber, account);
        
        System.out.println("\n✓ Account created successfully!");
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        ACCOUNT CREATED                 ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Customer ID: " + nextCustomerId);
        System.out.println("Account Number: " + nextAccountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.printf("Initial Balance: $%,.2f%n", initialDeposit);
        System.out.printf("Interest Rate: %.2f%%%n", account.getInterestRate() * 100);
        
        nextCustomerId++;
        nextAccountNumber++;
    }
    
    // 2. Deposit money
    private static void depositMoney() {
        System.out.println("\n╔══════ DEPOSIT MONEY ══════╗");
        
        int accountNumber = getIntInput("Enter account number: ");
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("✗ Account not found!");
            return;
        }
        
        Account account = accounts.get(accountNumber);
        double amount = getDoubleInput("Enter amount to deposit: $");
        
        account.deposit(amount);
    }
    
    // 3. Withdraw money
    private static void withdrawMoney() {
        System.out.println("\n╔══════ WITHDRAW MONEY ══════╗");
        
        int accountNumber = getIntInput("Enter account number: ");
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("✗ Account not found!");
            return;
        }
        
        Account account = accounts.get(accountNumber);
        double amount = getDoubleInput("Enter amount to withdraw: $");
        
        account.withdraw(amount);
    }
    
    // 4. Transfer money
    private static void transferMoney() {
        System.out.println("\n╔══════ TRANSFER MONEY ══════╗");
        
        int fromAccount = getIntInput("Enter source account number: ");
        
        if (!accounts.containsKey(fromAccount)) {
            System.out.println("✗ Source account not found!");
            return;
        }
        
        int toAccount = getIntInput("Enter destination account number: ");
        
        if (!accounts.containsKey(toAccount)) {
            System.out.println("✗ Destination account not found!");
            return;
        }
        
        if (fromAccount == toAccount) {
            System.out.println("✗ Cannot transfer to the same account!");
            return;
        }
        
        Account source = accounts.get(fromAccount);
        Account destination = accounts.get(toAccount);
        
        double amount = getDoubleInput("Enter amount to transfer: $");
        
        source.transfer(destination, amount);
    }
    
    // 5. View account details
    private static void viewAccountDetails() {
        System.out.println("\n╔══════ ACCOUNT DETAILS ══════╗");
        
        int accountNumber = getIntInput("Enter account number: ");
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("✗ Account not found!");
            return;
        }
        
        Account account = accounts.get(accountNumber);
        account.displayInfo();
    }
    
    // 6. View transaction history
    private static void viewTransactionHistory() {
        System.out.println("\n╔══════ TRANSACTION HISTORY ══════╗");
        
        int accountNumber = getIntInput("Enter account number: ");
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("✗ Account not found!");
            return;
        }
        
        Account account = accounts.get(accountNumber);
        account.displayTransactionHistory();
    }
    
    // 7. Request loan
    private static void requestLoan() {
        System.out.println("\n╔══════ REQUEST LOAN ══════╗");
        
        int accountNumber = getIntInput("Enter your account number: ");
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("✗ Account not found!");
            return;
        }
        
        Account account = accounts.get(accountNumber);
        
        // Check eligibility (balance should be at least 20% of loan)
        double loanAmount = getDoubleInput("Enter loan amount: $");
        
        if (loanAmount <= 0) {
            System.out.println("✗ Loan amount must be positive!");
            return;
        }
        
        double requiredBalance = loanAmount * 0.20;
        
        if (account.getBalance() < requiredBalance) {
            System.out.println("✗ Insufficient balance for loan eligibility!");
            System.out.printf("Required balance (20%% of loan): $%,.2f%n", requiredBalance);
            System.out.printf("Your balance: $%,.2f%n", account.getBalance());
            return;
        }
        
        double interestRate = getDoubleInput("Enter interest rate (e.g., 0.08 for 8%): ");
        int termMonths = getIntInput("Enter loan term in months: ");
        
        Loan loan = new Loan(accountNumber, loanAmount, interestRate, termMonths);
        loans.add(loan);
        
        System.out.println("\n✓ Loan request submitted!");
        loan.display();
        System.out.println("\nStatus: PENDING (awaiting approval)");
    }
    
    // 8. Pay loan
    private static void payLoan() {
        System.out.println("\n╔══════ PAY LOAN ══════╗");
        
        int accountNumber = getIntInput("Enter your account number: ");
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("✗ Account not found!");
            return;
        }
        
        // Find loans for this account
        ArrayList<Loan> accountLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getAccountNumber() == accountNumber && 
                (loan.getStatus().equals("APPROVED") || loan.getStatus().equals("PENDING"))) {
                accountLoans.add(loan);
            }
        }
        
        if (accountLoans.isEmpty()) {
            System.out.println("No active loans found for this account.");
            return;
        }
        
        System.out.println("\nYour Loans:");
        for (int i = 0; i < accountLoans.size(); i++) {
            Loan loan = accountLoans.get(i);
            System.out.printf("%d. Loan #%d - Amount: $%.2f, Remaining: $%.2f, Status: %s%n",
                i + 1, loan.getLoanId(), loan.getLoanAmount(), 
                loan.getRemainingAmount(), loan.getStatus());
        }
        
        int loanChoice = getIntInput("\nSelect loan number: ") - 1;
        
        if (loanChoice < 0 || loanChoice >= accountLoans.size()) {
            System.out.println("✗ Invalid selection!");
            return;
        }
        
        Loan loan = accountLoans.get(loanChoice);
        loan.display();
        
        double payment = getDoubleInput("\nEnter payment amount: $");
        
        if (loan.makePayment(payment)) {
            System.out.println("✓ Payment successful!");
            System.out.printf("Remaining balance: $%.2f%n", loan.getRemainingAmount());
            
            if (loan.getStatus().equals("PAID")) {
                System.out.println("🎉 Loan fully paid! Congratulations!");
            }
        }
    }
    
    // 9. Apply interest
    private static void applyInterest() {
        System.out.println("\n╔══════ APPLY INTEREST ══════╗");
        
        int accountNumber = getIntInput("Enter account number: ");
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("✗ Account not found!");
            return;
        }
        
        Account account = accounts.get(accountNumber);
        account.applyInterest();
    }
    
    // 10. Generate account statement
    private static void generateAccountStatement() {
        System.out.println("\n╔══════ ACCOUNT STATEMENT ══════╗");
        
        int accountNumber = getIntInput("Enter account number: ");
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("✗ Account not found!");
            return;
        }
        
        Account account = accounts.get(accountNumber);
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           SMARTBANK ACCOUNT STATEMENT                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n--- Account Information ---");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Account Holder: " + account.getCustomer().getName());
        System.out.println("Email: " + account.getCustomer().getEmail());
        System.out.printf("Current Balance: $%,.2f%n", account.getBalance());
        System.out.printf("Interest Rate: %.2f%%%n", account.getInterestRate() * 100);
        
        System.out.println("\n--- Transaction Summary ---");
        System.out.println("Total Transactions: " + account.getTransactions().size());
        
        // Calculate totals
        double totalDeposits = 0;
        double totalWithdrawals = 0;
        int depositCount = 0;
        int withdrawalCount = 0;
        
        for (Transaction tx : account.getTransactions()) {
            if (tx.getType().equals("DEPOSIT") || tx.getType().equals("TRANSFER_IN")) {
                totalDeposits += tx.getAmount();
                depositCount++;
            } else if (tx.getType().equals("WITHDRAWAL") || tx.getType().equals("TRANSFER_OUT")) {
                totalWithdrawals += tx.getAmount();
                withdrawalCount++;
            }
        }
        
        System.out.printf("Total Deposits: $%,.2f (%d transactions)%n", totalDeposits, depositCount);
        System.out.printf("Total Withdrawals: $%,.2f (%d transactions)%n", totalWithdrawals, withdrawalCount);
        
        // Show recent transactions
        System.out.println("\n--- Recent Transactions (Last 10) ---");
        ArrayList<Transaction> transactions = account.getTransactions();
        int start = Math.max(0, transactions.size() - 10);
        
        for (int i = start; i < transactions.size(); i++) {
            transactions.get(i).display();
        }
        
        System.out.println("\n════════════════════════════════════════════════════════════════════════════════");
        System.out.println("                        End of Statement");
        System.out.println("════════════════════════════════════════════════════════════════════════════════");
    }
    
    // 11. View all accounts (Admin)
    private static void viewAllAccounts() {
        System.out.println("\n╔══════════ ALL ACCOUNTS ══════════╗");
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the system!");
            return;
        }
        
        System.out.println("\nTotal Accounts: " + accounts.size());
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-8s %-20s %-15s %-15s %-10s%n", 
            "Acc #", "Customer", "Type", "Balance", "Status");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
        
        for (Account account : accounts.values()) {
            System.out.printf("%-8d %-20s %-15s $%-14,.2f %-10s%n",
                account.getAccountNumber(),
                account.getCustomer().getName(),
                account.getAccountType(),
                account.getBalance(),
                (account.isActive() ? "Active" : "Closed"));
        }
        
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
    }
    
    // 12. Search accounts
    private static void searchAccounts() {
        System.out.println("\n╔══════ SEARCH ACCOUNTS ══════╗");
        
        System.out.println("Search by:");
        System.out.println("1. Customer Name");
        System.out.println("2. Account Type");
        System.out.println("3. Balance Range");
        
        int choice = getIntInput("Enter choice: ");
        scanner.nextLine();  // Clear buffer
        
        switch (choice) {
            case 1:
                System.out.print("Enter customer name (or part of name): ");
                String searchName = scanner.nextLine().toLowerCase();
                
                System.out.println("\n--- Search Results ---");
                boolean found = false;
                
                for (Account account : accounts.values()) {
                    if (account.getCustomer().getName().toLowerCase().contains(searchName)) {
                        System.out.printf("Account %d | %s | Balance: $%,.2f%n",
                            account.getAccountNumber(),
                            account.getCustomer().getName(),
                            account.getBalance());
                        found = true;
                    }
                }
                
                if (!found) {
                    System.out.println("No accounts found matching: " + searchName);
                }
                break;
                
            case 2:
                System.out.print("Enter account type (SAVINGS/CHECKING/FIXED_DEPOSIT): ");
                String type = scanner.nextLine().toUpperCase();
                
                System.out.println("\n--- " + type + " Accounts ---");
                int count = 0;
                
                for (Account account : accounts.values()) {
                    if (account.getAccountType().equals(type)) {
                        System.out.printf("Account %d | %s | Balance: $%,.2f%n",
                            account.getAccountNumber(),
                            account.getCustomer().getName(),
                            account.getBalance());
                        count++;
                    }
                }
                
                System.out.println("\nTotal " + type + " accounts: " + count);
                break;
                
            case 3:
                double minBalance = getDoubleInput("Enter minimum balance: $");
                double maxBalance = getDoubleInput("Enter maximum balance: $");
                
                System.out.println("\n--- Accounts in Range ---");
                int rangeCount = 0;
                
                for (Account account : accounts.values()) {
                    double balance = account.getBalance();
                    if (balance >= minBalance && balance <= maxBalance) {
                        System.out.printf("Account %d | %s | Balance: $%,.2f%n",
                            account.getAccountNumber(),
                            account.getCustomer().getName(),
                            balance);
                        rangeCount++;
                    }
                }
                
                System.out.println("\nTotal accounts in range: " + rangeCount);
                break;
                
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    // 13. Manage loans (Admin)
    private static void manageLoan() {
        System.out.println("\n╔══════ MANAGE LOANS ══════╗");
        
        if (loans.isEmpty()) {
            System.out.println("No loans in the system!");
            return;
        }
        
        // Show all loans
        System.out.println("\nAll Loans:");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.printf("%-8s %-10s %-15s %-15s %-10s%n", 
            "Loan ID", "Account", "Amount", "Remaining", "Status");
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        for (int i = 0; i < loans.size(); i++) {
            Loan loan = loans.get(i);
            System.out.printf("%-8d %-10d $%-14,.2f $%-14,.2f %-10s%n",
                loan.getLoanId(),
                loan.getAccountNumber(),
                loan.getLoanAmount(),
                loan.getRemainingAmount(),
                loan.getStatus());
        }
        
        int loanId = getIntInput("\nEnter Loan ID to manage: ");
        
        Loan selectedLoan = null;
        for (Loan loan : loans) {
            if (loan.getLoanId() == loanId) {
                selectedLoan = loan;
                break;
            }
        }
        
        if (selectedLoan == null) {
            System.out.println("✗ Loan not found!");
            return;
        }
        
        selectedLoan.display();
        
        if (!selectedLoan.getStatus().equals("PENDING")) {
            System.out.println("\nThis loan has already been processed.");
            return;
        }
        
        System.out.println("\n1. Approve Loan");
        System.out.println("2. Reject Loan");
        
        int action = getIntInput("Enter choice: ");
        
        switch (action) {
            case 1:
                selectedLoan.approve();
                System.out.println("✓ Loan approved!");
                break;
            case 2:
                selectedLoan.reject();
                System.out.println("✓ Loan rejected!");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    // 14. System statistics
    private static void systemStatistics() {
        System.out.println("\n╔══════════ SYSTEM STATISTICS ══════════╗");
        
        if (accounts.isEmpty()) {
            System.out.println("No data available!");
            return;
        }
        
        int totalAccounts = accounts.size();
        int activeAccounts = 0;
        int savingsCount = 0;
        int checkingCount = 0;
        int fdCount = 0;
        double totalBalance = 0;
        double highestBalance = 0;
        double lowestBalance = Double.MAX_VALUE;
        Account richestAccount = null;
        
        for (Account account : accounts.values()) {
            if (account.isActive()) {
                activeAccounts++;
                totalBalance += account.getBalance();
                
                switch (account.getAccountType()) {
                    case "SAVINGS": savingsCount++; break;
                    case "CHECKING": checkingCount++; break;
                    case "FIXED_DEPOSIT": fdCount++; break;
                }
                
                if (account.getBalance() > highestBalance) {
                    highestBalance = account.getBalance();
                    richestAccount = account;
                }
                
                if (account.getBalance() < lowestBalance) {
                    lowestBalance = account.getBalance();
                }
            }
        }
        
        double averageBalance = activeAccounts > 0 ? totalBalance / activeAccounts : 0;
        
        // Transaction statistics
        int totalTransactions = 0;
        for (Account account : accounts.values()) {
            totalTransactions += account.getTransactions().size();
        }
        
        // Loan statistics
        int totalLoans = loans.size();
        int pendingLoans = 0;
        int approvedLoans = 0;
        int rejectedLoans = 0;
        int paidLoans = 0;
        
        for (Loan loan : loans) {
            switch (loan.getStatus()) {
                case "PENDING": pendingLoans++; break;
                case "APPROVED": approvedLoans++; break;
                case "REJECTED": rejectedLoans++; break;
                case "PAID": paidLoans++; break;
            }
        }
        
        System.out.println("\n--- Account Statistics ---");
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.println("Active Accounts: " + activeAccounts);
        System.out.println("SAVINGS Accounts: " + savingsCount);
        System.out.println("CHECKING Accounts: " + checkingCount);
        System.out.println("FIXED_DEPOSIT Accounts: " + fdCount);
        
        System.out.println("\n--- Financial Statistics ---");
        System.out.printf("Total Bank Balance: $%,.2f%n", totalBalance);
        System.out.printf("Average Balance: $%,.2f%n", averageBalance);
        System.out.printf("Highest Balance: $%,.2f%n", highestBalance);
        System.out.printf("Lowest Balance: $%,.2f%n", lowestBalance);
        
        if (richestAccount != null) {
            System.out.println("\n--- Top Account Holder ---");
            System.out.println("Name: " + richestAccount.getCustomer().getName());
            System.out.println("Account: " + richestAccount.getAccountNumber());
            System.out.printf("Balance: $%,.2f%n", richestAccount.getBalance());
        }
        
        System.out.println("\n--- Transaction Statistics ---");
        System.out.println("Total Transactions: " + totalTransactions);
        System.out.printf("Average Transactions per Account: %.1f%n", 
            activeAccounts > 0 ? (double)totalTransactions / activeAccounts : 0);
        
        System.out.println("\n--- Loan Statistics ---");
        System.out.println("Total Loans: " + totalLoans);
        System.out.println("Pending Loans: " + pendingLoans);
        System.out.println("Approved Loans: " + approvedLoans);
        System.out.println("Rejected Loans: " + rejectedLoans);
        System.out.println("Paid Loans: " + paidLoans);
    }
    
    // 15. Save all data
    private static void saveAllData() {
        System.out.println("\n╔══════ SAVING DATA ══════╗");
        
        try {
            // Save customers
            try (FileWriter writer = new FileWriter(CUSTOMERS_FILE)) {
                for (Customer customer : customers.values()) {
                    writer.write(customer.getCustomerId() + "," + 
                               customer.getName() + "," + 
                               customer.getEmail() + "," + 
                               customer.getPhoneNumber() + "\n");
                }
            }
            
            // Save accounts
            try (FileWriter writer = new FileWriter(ACCOUNTS_FILE)) {
                for (Account account : accounts.values()) {
                    writer.write(account.toFileString() + "\n");
                }
            }
            
            // Save transactions
            try (FileWriter writer = new FileWriter(TRANSACTIONS_FILE)) {
                for (Account account : accounts.values()) {
                    for (Transaction tx : account.getTransactions()) {
                        writer.write(tx.toFileString() + "\n");
                    }
                }
            }
            
            // Save loans
            try (FileWriter writer = new FileWriter(LOANS_FILE)) {
                for (Loan loan : loans) {
                    writer.write(loan.toFileString() + "\n");
                }
            }
            
            System.out.println("✓ All data saved successfully!");
            System.out.println("Customers: " + customers.size());
            System.out.println("Accounts: " + accounts.size());
            System.out.println("Loans: " + loans.size());
            
        } catch (IOException e) {
            System.out.println("✗ Error saving data: " + e.getMessage());
        }
    }
    
    // Load all data
    private static void loadAllData() {
        System.out.println("\n╔══════ LOADING DATA ══════╗");
        
        try {
            // Load customers
            File customersFile = new File(CUSTOMERS_FILE);
            if (customersFile.exists()) {
                Scanner fileScanner = new Scanner(customersFile);
                int customerCount = 0;
                int maxCustomerId = 1000;
                
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        String[] parts = line.split(",");
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        String email = parts[2].trim();
                        String phone = parts[3].trim();
                        
                        Customer customer = new Customer(name, id, email, phone);
                        customers.put(id, customer);
                        customerCount++;
                        
                        if (id > maxCustomerId) {
                            maxCustomerId = id;
                        }
                    }
                }
                
                nextCustomerId = maxCustomerId + 1;
                fileScanner.close();
                System.out.println("✓ Loaded " + customerCount + " customers");
            }
            
            // Load accounts
            File accountsFile = new File(ACCOUNTS_FILE);
            if (accountsFile.exists()) {
                Scanner fileScanner = new Scanner(accountsFile);
                int accountCount = 0;
                int maxAccountNum = 5000;
                
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        String[] parts = line.split(",");
                        int accNum = Integer.parseInt(parts[0].trim());
                        int custId = Integer.parseInt(parts[3].trim());
                        
                        Customer customer = customers.get(custId);
                        if (customer != null) {
                            Account account = Account.fromFileString(line, customer);
                            if (account != null) {
                                accounts.put(accNum, account);
                                accountCount++;
                                
                                if (accNum > maxAccountNum) {
                                    maxAccountNum = accNum;
                                }
                            }
                        }
                    }
                }
                
                nextAccountNumber = maxAccountNum + 1;
                fileScanner.close();
                System.out.println("✓ Loaded " + accountCount + " accounts");
            }
            
            // Load transactions
            File transactionsFile = new File(TRANSACTIONS_FILE);
            if (transactionsFile.exists()) {
                Scanner fileScanner = new Scanner(transactionsFile);
                int txCount = 0;
                
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        Transaction tx = Transaction.fromFileString(line);
                        if (tx != null) {
                            Account account = accounts.get(tx.getAccountNumber());
                            if (account != null) {
                                account.getTransactions().add(tx);
                                txCount++;
                            }
                        }
                    }
                }
                
                fileScanner.close();
                System.out.println("✓ Loaded " + txCount + " transactions");
            }
            
            // Load loans
            File loansFile = new File(LOANS_FILE);
            if (loansFile.exists()) {
                Scanner fileScanner = new Scanner(loansFile);
                int loanCount = 0;
                
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        Loan loan = Loan.fromFileString(line);
                        if (loan != null) {
                            loans.add(loan);
                            loanCount++;
                        }
                    }
                }
                
                fileScanner.close();
                System.out.println("✓ Loaded " + loanCount + " loans");
            }
            
            if (!customersFile.exists() && !accountsFile.exists()) {
                System.out.println("No saved data found. Starting fresh!");
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error loading data: " + e.getMessage());
        }
    }
    
    // Helper: Get integer input with validation
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();  // Clear buffer
                return value;
                
            } catch (InputMismatchException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }
    
    // Helper: Get double input with validation
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();  // Clear buffer
                return value;
                
            } catch (InputMismatchException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }
}