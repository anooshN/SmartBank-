import java.util.ArrayList;
import java.util.Scanner;

public class OOPBankingSystem{
	private static ArrayList<BankAccount> accounts = new ArrayList();
	private static Scanner scanner = new Scanner(System.in);
	private static int nextAccountNumber = 5001;
	
	public static void main(String[] args) {
		initializeSampleData();
		
		System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     SMARTBANK - OOP BANKING SYSTEM     ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        boolean running = true;
        
        while(running) {
        	
        		displayMainMenu();
        		int choice = getIntInput("enter your choice:");
        		
        		switch(choice) {
        		
	        		case 1:
	        			createNewAccount();
	        			break;
	        		
	        		case 2:
	        			displayAllAccounts();
	        			break;
	        		case 3:
	        			depositMoney();
	        			break;
	        		case 4:
	        			withdrawMoney();
	        			break;
	        		case 5:
	        			transferMoney();
	        			break;
	        		case 6:
	        			checkAccountDetails();
	        			break;
	        		case 7:
	        			applyInterestToAccount();
	        			break;
	        		case 8:
	        			checkLoanEligibility();
	        			break;
	        		case 9:
	        			closeAccount();
	        			break;
	        		case 10:
	        			System.out.println("\n thank you for using smartBank!");
	        			System.out.println("have a great day!\n");
	        			running = false;
	        			break;
	        		default:
	        			System.out.println("invalid choice! please try again.\n");
        		
        		}
        }
        
        scanner.close();
	}
	
	public static void initializeSampleData() {
		Customer c1 = new Customer("Anoosh",1001,"anoosh@smartbank.com","000-000-0000");
		BankAccount acc1 = new BankAccount(5001,"savings",10000,c1);
		accounts.add(acc1);
		
		Customer c2 = new Customer("john doe", 1002, "jhon@smartbank.com","000-000-0000");
		BankAccount acc2 = new BankAccount(5002,"checking",5000,c2);
		accounts.add(acc2);
		
		Customer c3 = new Customer("sarah smith",1003, "sarah@smartbank.com","000-000-0000");
		BankAccount acc3 = new BankAccount(5003,"savings",1500,c3);
		accounts.add(acc3);
		
		nextAccountNumber = 5004;
	}
	
	private static void displayMainMenu() {
		System.out.println("\n========== Main Menu ===========");
		System.out.println("1. Create New Account");
		System.out.println("2. Display all accounts");
		System.out.println("3.Deposit Money");
		System.out.println("4.withdraw money");
		System.out.println("5.transfer money");
		System.out.println("6.check account details");
		System.out.println("7. apply interest");
		System.out.println("8. check loan eligibility");
		System.out.println("9. close account");
		System.out.println("10.exit");
		System.out.println("======================================");
	}
	
	private static void createNewAccount() {
		System.out.println("\n========= create new account ========");
		
		scanner.nextLine();
		
		System.out.println("enter customer name: ");
		String name = scanner.nextLine();
		
		int customerId = 1000 + accounts.size() + 1;
		
		System.out.println("enter email: ");
		String email = scanner.nextLine();
		
		System.out.println("enter phone number:");
		String phone =  scanner.nextLine().toUpperCase();
		
		System.out.println(" enter account type : ");
		String accountType = scanner.next();
		
		double initialDeposit = getDoubleInput("Enter initial deposit amount: $");
		
		if(initialDeposit < 100) {
			System.out.println("minimum initial deposit is $100!");
			return;
		}
		
		Customer customer = new Customer(name, customerId, email, phone);
		BankAccount account = new BankAccount(nextAccountNumber, accountType, initialDeposit,customer);
		
		accounts.add(account);
		nextAccountNumber++;
		System.out.println("\n Account created successfuly!");
		System.out.println("account number: "+account.getAccountNumber());
		System.out.println("Customer Id: "+customerId);
		System.out.printf("initial balance: $%,.2f%n", initialDeposit);
		}
	
	private static void displayAllAccounts() {
		System.out.println("\n======= all bank accounts========");
		
		if(accounts.isEmpty()) {
			System.out.println("no accounts found!");
			return;
		}
		
		System.out.println("\n total accounts: "+accounts.size());
		System.out.println("==============================================================");
		System.out.printf("%-8s %-20s %-12s %-15s %-10s%n","Acc #", "Customer", "Type","Balance","Status");
		System.out.println("===================================================================");
		
		for(BankAccount account : accounts) {
			System.out.printf("%-8d %-20s %-12s $%,14.2f %-10s%n",
	                account.getAccountNumber(),
	                account.getCustomer().getName(),
	                account.getAccountType(),
	                account.getBalance(),
	                (account.isActive() ? "Active" : "Closed"));
		}
		
		System.out.println("==========================================================");
		
		double totalbalance =0;
		for(BankAccount account : accounts) {
			if(account.isActive()) {
				totalbalance += account.getBalance();
			}
		}
		System.out.printf("\ntotal bank balance: $%,.2f%n", totalbalance);
		
	}
	
	private static void depositMoney() {
		System.out.println("\n ====== deposit money ==========");
		
		int accountNumber = getIntInput("enter account number: ");
		BankAccount account = findAccount(accountNumber);
		
		if(account == null) {
			System.out.println("x account not found");
			return ;
		}
		
		double amount = getDoubleInput("enter amount to deposit: $");
		account.deposit(amount);
	}
	
	private static void withdrawMoney() {
		System.out.println("\n ====== withdraw money ======");
		
		int accountNumber = getIntInput("enter account number:");
		BankAccount account = findAccount(accountNumber);
		
		if(account == null) {
			System.out.println("x account not found!");
			return;
		}
		
		double amount = getDoubleInput("enter amount to withdraw: $");
		account.withdraw(amount);
	}
	
	private static void transferMoney() {
		System.out.println("\n======= transfer money ==========");
		
		int fromAccountNumber = getIntInput("enter source account number:");
		BankAccount fromAccount = findAccount(fromAccountNumber);
		
		if(fromAccount == null) {
			System.out.println("x source account not found!");
			return;
			
		}
		
		int toAccountNumber = getIntInput("enter distination account number:");
		BankAccount toAccount = findAccount(toAccountNumber);
		
		if(toAccount == null) {
			System.out.println("x destination account not found :");
			return;
		}
		
		if(fromAccountNumber == toAccountNumber) {
			System.out.println("x account transfer to the same account!");
			return;
		}
		
		double amount = getDoubleInput("enter amount to transfer: $");
		fromAccount.transfer(toAccount,amount);
	}
	
	private static void checkAccountDetails() {
		System.out.println("\n=========== account details =======");
		
		int accountNumber = getIntInput("enter account number:");
		BankAccount account = findAccount(accountNumber);
		
		if(account == null) {
			System.out.println("x account not found!");
			return;
		}
		account.displayAccountInfo();
	}
	
	
	private static void applyInterestToAccount() {
		System.out.println("\n======= apply interest ==========");
		
		int accountNumber = getIntInput("enter account number!");
		BankAccount account = findAccount(accountNumber);
		
		if(account == null) {
			System.out.println("x account not found!");
			return;
		}
		
		double rate = getDoubleInput("enter annual interest rate (e.g., 0.05 for 5%): ");
		int years = getIntInput("enter number of years: ");
		
		account.applyInterest(rate, years);
		
	}
	
	private static void checkLoanEligibility() {
		System.out.println("\n===== loan eligibility check========");
		
		int accountNumber = getIntInput("enter account number!");
		BankAccount account = findAccount(accountNumber);
		
		if(account == null) {
			System.out.println("x account not found!");
			return;
		}
		
		double loanAmount = getDoubleInput("enter desired loan amount : $");
		
		boolean eligible = account.isEligibleforLoan(loanAmount);
		
		System.out.println("\nAccount Holder: " + account.getCustomer().getName());
        System.out.printf("Current Balance: $%,.2f%n", account.getBalance());
        System.out.printf("Requested Loan: $%,.2f%n", loanAmount);
        System.out.printf("Required Balance (20%%): $%,.2f%n", loanAmount * 0.20);
        
        if(eligible ) {
        	System.out.println("\n eligible for loan!");
        }else {
        		System.out.println("\n not eligible for loan!");
        		System.out.println("reason: insuffienct balance or inactive account");
        }
	}
	
	private static void closeAccount() {
		System.out.println("\n========= close account ======");
		
		int accountNumber = getIntInput("enter account number to close:");
		
		BankAccount account = findAccount(accountNumber);
		
		if(account == null) {
			System.out.println("x account not found");
			return;
		}
		
		System.out.println("\nAccount: " + accountNumber);
        System.out.println("Customer: " + account.getCustomer().getName());
        System.out.printf("Balance: $%,.2f%n", account.getBalance());
        
        System.out.print("\nAre you sure you want to close this account? (yes/no): ");
        scanner.nextLine();  // Clear buffer
        String confirmation = scanner.nextLine();
		
        if(confirmation.equalsIgnoreCase("yes")){
        			account.closeAccount();
        }else {

        		System.out.println("account closure cancelled");
        }
	}
	
	
	private static BankAccount findAccount(int accountNumber) {
		for(BankAccount account : accounts) {
			if(account.getAccountNumber() ==accountNumber) {
				return account;
			}
		}
		return null;
	}
	
	private static int getIntInput(String prompt) {
		System.out.println(prompt);
		while(!scanner.hasNextInt()) {
			System.out.println("x invalid input! please enter a number!");
			scanner.next();
			
		}
		return scanner.nextInt();
	}
	
	private static double getDoubleInput(String prompt) {
		System.out.println(prompt);
		while(!scanner.hasNextDouble()) {
			System.out.println("x invalid input! please enter a number: ");
			scanner.next();
		}
		return scanner.nextDouble();
	}
	
	
	
	
}