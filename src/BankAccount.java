import java.nio.channels.NonWritableChannelException;

public class BankAccount{
	private int accountNumber;
	private String accountType;
	private double balance;
	private Customer customer;
	private boolean isActive;
	
	public BankAccount() {
		this.accountNumber = 0;
		this.accountType = "savings";
		this.balance = 0.0;
		this.customer = new Customer();
		this.isActive = true;
	}
	
	public BankAccount(int accountNumber, String accountType,double initialBalance, Customer customer) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = initialBalance;
		this.customer = customer;
		this.isActive = true;
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
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
	}
	
	
	public boolean deposit(double amount) {
		try{
		if(!isActive) {
		
//			System.out.println("x account is closed!");
//			return false;
			throw new AccountClosedEception(accountNumber, "deposit");
		}
		
		if(amount <= 0) {
//			System.out.println("x deposit amount must be positive!");
//			return false;
			throw new InvalidAmountException(amount, "deposit");
		}
		if(amount > 100000) {
			throw new InvalidAmountException(amount,"deposit (exceeds maximum)");
		}
		balance += amount;
		System.out.println(" Deposit successful!");
		System.out.printf("amount deposited: $%,.2f%n",amount);
		System.out.printf("new balance: $%, .2f%n", balance);
		return true;
		
	}catch (AccountClosedEception e) {
		// TODO: handle exception
		e.displayErrorDetails();
		return false;
	}catch (InvalidAmountException e) {
		// TODO: handle exception
		e.displayErrorDetails();
		return false;
	}
	}
	
	public boolean withdraw(double amount) {
		
		try {
		if(!isActive) {
//			System.out.println("x account is closed!");
//			return false;
			throw new AccountClosedEception(accountNumber, "withdrawl");
		}
		
		if(amount <= 0) {
//			System.out.println("x withdrawl amount must be positive!");
//			return false;
			throw new InvalidAmountException(amount, "withdrawal");
		}
		
		if(amount > balance) {
//			System.out.println("x insuffienct funds");
//			System.out.printf("current balance: $%,.2f%n ",balance);
//			System.out.printf("attempted withdraw : $%, .2f%n", amount);
//			return false;
			throw new InsufficientFundsException(amount, balance);
		}
		
		balance -= amount;
		System.out.println("withdrawal successful!");
		System.out.printf("amount withdraw: $%,.2f%n", amount);
		System.out.printf("new balance: $%, .2f%n", balance);
		return true;
	}catch (AccountClosedEception e) {
		// TODO: handle exception
		return false;
	}catch (InvalidAmountException e) {
		// TODO: handle exception
		return false;
	}catch (InsufficientFundsException e) {
		// TODO: handle exception
		return false;
	}
	}
	
	public boolean transfer(BankAccount targetAccount, double amount) {
		
		try {
		if(!isActive) {
//			System.out.println("x source accunt is closed!");
//			return false;
			throw new AccountClosedEception(accountNumber, "transfer (source)");
		}
		if(targetAccount == null) {
			throw new InvalidAccountException(0);
		}
		
		if(!targetAccount.isActive()) {
//			System.out.println("x target account is closed");
//			return false;
			throw new AccountClosedEception(targetAccount.getAccountNumber(), "transfer (destination)");
		}
		
		if(amount <= 0) {
//			System.out.println("x transfer amount must be positive!");
//			return false;
			throw new InvalidAmountException(amount, "tranfer");
		}
		
		if(amount > 1000000) {
			throw new InvalidAmountException(amount, "transfer (exceeds maximum)");
		}
		if(amount > balance) {
//			System.out.println("x insufficient funds for transfer!");
//			System.out.printf("current balance: $%, .2f%n",balance);
//			return false;
			throw new InsufficientFundsException(amount, balance);
		}
		
		balance -=amount;
		targetAccount.balance += amount;
		
		System.out.println(" Transfer succesful!");
		System.out.printf("from: %s (accunt : %d)%n", customer.getName(), accountNumber);
		System.out.printf("to: %s (account: %d)%n", targetAccount.customer.getName(), targetAccount.accountNumber);
		System.out.printf("amount: $%, .2f%n", amount);
		System.out.printf("your new balance: $%, .2f%n", balance);
		return true;
	}catch (AccountClosedEception e) {
		// TODO: handle exception
		e.displayErrorDetails();
		return false;
	}catch (InvalidAccountException e) {
		// TODO: handle exception
		e.displayErrorDetails();
		return false;
	}catch (InvalidAmountException e) {
		e.displayErrorDetails();
		return false;
	}catch(InsufficientFundsException e) {
		e.displayErrorDetails();
		return false;
	}
	}
	
	
	public void validateAccount() throws AccountClosedEception{
		if(!isActive) {
			throw new AccountClosedEception(accountNumber, "operation");
		}
	}
	public double calculateInterest(double annualRate, int years) {
		double interest = balance * annualRate * years;
		return interest;
		
	}
	
	public void applyInterest(double annualRate, int years) {
		double interest = calculateInterest(annualRate, years);
		balance += interest;
		System.out.printf(" Interest applied: $%,.2f%n", interest);
		System.out.printf("new balance : $%, .2f%n", balance);
	}
	
	public void closeAccount() {
		if(!isActive) {
			System.out.println("accunt is already closed!");
			return;
		}
		
		if(balance > 0) {
			System.out.println(" warning : account has remaining balance of $"+balance);
			System.out.println("please withdraw all funds before closing.");
			return;
		}
		
		isActive = false;
		System.out.println("account closed sucessfully!");
	}
	
	public void displayAccountInfo() {
		System.out.println("\n=============== accunt details ============");
		System.out.println("account number: " +accountNumber);
		System.out.println("account type : "+accountType);
		System.out.printf("Balance: $%, .2f%n", balance);
		System.out.println("status: "+ (isActive ? "active" : "closed"));
		System.out.println("\n=== Account Holder====");
		customer.displayInfo();
		System.out.println("=================================================");
		
	}
	
	public boolean isEligibleforLoan(double loanAmount) {
		double requiredBalance = loanAmount * 0.20;
		return balance >= requiredBalance && isActive;
		
	}
	
	
	
}