import java.util.ArrayList;

public class Account{
	private int accountNumber;
	private String accountType;
	private double balance;
	private Customer customer;
	private boolean isActive;
	private ArrayList<Transaction> transactions;
	private double interestRate;
	
	
	public Account(int accountNumber, String accountType, double initialbalance, Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = initialbalance;
		this.customer = customer;
		this.isActive = true;
		this.transactions = new ArrayList<Transaction>();
		switch(accountType) {
				case "SAVINGS":
					this.interestRate=0.035;
					break;
				case "CHECKING":
					this.interestRate=0.01;
					break;
				case "FIXED_DEPOSIT":
					this.interestRate=0.065;
					break;
				default:
					this.interestRate=0.01;
		}
		
		if(initialbalance > 0) {
			Transaction tx= new Transaction(accountNumber, "DEPOSIT", initialbalance,balance, "intitial Deposit");
			transactions.add(tx);
		}
	}


	public int getAccountNumber() {
		return accountNumber;
	}


	public String getAccountType() {
		return accountType;
	}


	public double getBalance() {
		return balance;
	}


	public Customer getCustomer() {
		return customer;
	}


	public boolean isActive() {
		return isActive;
	}


	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}


	public double getInterestRate() {
		return interestRate;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean deposit(double amount) {
		try {
			if(!isActive) {
				throw new AccountClosedException(accountNumber,"deposit");
			}
			if (amount <= 0) {
				throw new InvalidAmountException(amount,"deposit");
			}
			 
			balance+=amount;
			
			Transaction tx = new Transaction(accountNumber, "DEPOSIT", amount, balance, "deposit");
			transactions.add(tx);
			
			System.out.println("Deposit successful!");
			System.out.printf("amount deposited: $%,.2f%n",amount);
			System.out.printf("new balance: $%,.2f%n",balance);
			return true;
		}catch (AccountClosedException | InvalidAmountException e) {
			// TODO: handle exception
			if(e instanceof AccountClosedException) {
				((AccountClosedException)e).displayErrorDetails();
			}else {
				((InvalidAmountException)e).displayErrorDetails();
			}
			return false;
		}
	}
	
	public boolean withdraw(double amount) {
		try {
			if(!isActive) {
				throw new AccountClosedException(accountNumber,"withdrawal");
			}
			if(amount<=0) {
				throw new InvalidAmountException(amount,"withdrawl");
			}
			if(amount>balance) {
				throw new InsufficientFundsException(amount,balance);
			}
			
			balance-=amount;
			
			Transaction tx= new Transaction(accountNumber, "WITHDRAWAL", amount, balance, "WITHDRAWAL");
			transactions.add(tx);
			
			System.out.println("withdrawal successful!");
			System.out.printf("amount withdrawal:$%,.2f%n",amount);
			System.out.printf("new balance:$%,.2f%n",balance);
			return true;
		}catch (AccountClosedException | InvalidAmountException | InsufficientFundsException e) {
			// TODO: handle exception
			if(e instanceof AccountClosedException) {
				((AccountClosedException)e).displayErrorDetails();
			}else if (e instanceof InvalidAmountException) {
                ((InvalidAmountException) e).displayErrorDetails();
            } else {
                ((InsufficientFundsException) e).displayErrorDetails();
            }
			return false;
		}
	}
	
	public boolean transfer(Account targetAccount, double amount) {
        try {
            if (!isActive) {
                throw new AccountClosedException(accountNumber, "transfer (source)");
            }
            
            if (!targetAccount.isActive()) {
                throw new AccountClosedException(targetAccount.getAccountNumber(), "transfer (destination)");
            }
            
            if (amount <= 0) {
                throw new InvalidAmountException(amount, "transfer");
            }
            
            if (amount > balance) {
                throw new InsufficientFundsException(amount, balance);
            }
            
            // Deduct from source
            balance -= amount;
            Transaction txOut = new Transaction(accountNumber, "TRANSFER_OUT", amount, balance, 
                "Transfer to account " + targetAccount.getAccountNumber());
            transactions.add(txOut);
            
            // Add to destination
            targetAccount.balance += amount;
            Transaction txIn = new Transaction(targetAccount.getAccountNumber(), "TRANSFER_IN", amount, 
                targetAccount.balance, "Transfer from account " + accountNumber);
            targetAccount.transactions.add(txIn);
            
            System.out.println("✓ Transfer successful!");
            System.out.printf("From: %s (Account: %d)%n", customer.getName(), accountNumber);
            System.out.printf("To: %s (Account: %d)%n", targetAccount.customer.getName(), targetAccount.accountNumber);
            System.out.printf("Amount: $%,.2f%n", amount);
            System.out.printf("Your new balance: $%,.2f%n", balance);
            
            return true;
            
        } catch (AccountClosedException | InvalidAmountException | InsufficientFundsException e) {
            if (e instanceof AccountClosedException) {
                ((AccountClosedException) e).displayErrorDetails();
            } else if (e instanceof InvalidAmountException) {
                ((InvalidAmountException) e).displayErrorDetails();
            } else {
                ((InsufficientFundsException) e).displayErrorDetails();
            }
            return false;
        }
    }
    
    // Apply interest
    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        
        Transaction tx = new Transaction(accountNumber, "INTEREST", interest, balance, 
            String.format("Interest applied (%.2f%%)", interestRate * 100));
        transactions.add(tx);
        
        System.out.printf("✓ Interest applied: $%,.2f%n", interest);
        System.out.printf("New balance: $%,.2f%n", balance);
    }
    
    // Display account info
    public void displayInfo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         ACCOUNT INFORMATION            ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.printf("Balance: $%,.2f%n", balance);
        System.out.printf("Interest Rate: %.2f%%%n", interestRate * 100);
        System.out.println("Status: " + (isActive ? "Active" : "Closed"));
        System.out.println("Total Transactions: " + transactions.size());
        System.out.println("\n--- Account Holder ---");
        customer.displayInfo();
    }
    
    // Display transaction history
    public void displayTransactionHistory() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           TRANSACTION HISTORY                                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("Account: " + accountNumber + " | " + customer.getName());
        
        if (transactions.isEmpty()) {
            System.out.println("\nNo transactions yet.");
            return;
        }
        
        System.out.println("\n" + transactions.size() + " transaction(s) found:");
        System.out.println("════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-8s %-15s %-20s %-14s %-14s %s%n", 
            "TX ID", "Type", "Date/Time", "Amount", "Balance", "Description");
        System.out.println("════════════════════════════════════════════════════════════════════════════════");
        
        for (Transaction tx : transactions) {
            tx.display();
        }
        
        System.out.println("════════════════════════════════════════════════════════════════════════════════");
    }
    
    // Convert to file string
    public String toFileString() {
        return accountNumber + "," + accountType + "," + balance + "," + 
               customer.getCustomerId() + "," + isActive + "," + interestRate;
    }
    
    // Create from file string
    public static Account fromFileString(String line, Customer customer) {
        try {
            String[] parts = line.split(",");
            
            int accNum = Integer.parseInt(parts[0].trim());
            String type = parts[1].trim();
            double bal = Double.parseDouble(parts[2].trim());
            boolean active = Boolean.parseBoolean(parts[4].trim());
            
            Account account = new Account(accNum, type, bal, customer);
            account.isActive = active;
            account.transactions.clear();  // Clear initial transaction, will load from file
            
            return account;
            
        } catch (Exception e) {
            System.out.println("Error parsing account: " + line);
            return null;
        }
    }
}
	
	
	
	
	
	
