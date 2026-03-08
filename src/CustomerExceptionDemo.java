public class CustomerExceptionDemo{
	public static void main(String[] args) {
		System.out.println("==== custom exception demo =====");
		
		System.out.println("----- test 1: insufficient funds -----");
		
		try {
			simulateWithdrawal(10000,5000);
			
		}catch(InsufficientFundsException e) {
			e.displayErrorDetails();
		}
		
		System.out.println("\n\n--- test 2: invalid account ----");
		
		try {
			findAccount(9999);
		}catch(InvalidAccountException e) {
			e.displayErrorDetails();
		}
		
		System.out.println("\n\n---- test 3 : invalid amount ----");
		
		try {
			validateAmount(-500, "deposit");
		}catch(InvalidAmountException e) {
			e.displayErrorDetails();
		}
		
		System.out.println("\n");
		
		try {
			validateAmount(2000000,"withdrawal");
		}catch (InvalidAmountException e) {
			// TODO: handle exception
			e.displayErrorDetails();
		}
		
		System.out.println("\n\n--- test 4: account closed ---");
		
		try {
			performOperationOnClosedAccount(5001,"withdrawl");
			
		}catch (AccountClosedEception e) {
			// TODO: handle exception
			e.displayErrorDetails();
		}
		
		System.out.println("\n\n --- test 5: multiple exception handling----");
		
		processTransaction(5001,1000,true);
		processTransaction(9999, 500, true);
		processTransaction(5002, -100, true);    // Invalid amount
	    processTransaction(5003, 500, false);    // Account closed
	    processTransaction(5004, 500, true);
	        
	        System.out.println("\n====== custom exception demo complete====");
		
	}
	
	static void simulateWithdrawal(double amount, double balance)throws InsufficientFundsException {
		if(amount>balance) {
			throw new InsufficientFundsException(amount, balance);
		}
		System.out.println("withdrawal succesful!");
		System.out.printf("withdrawal $%,.2f. New balance: $%,.2f%n", amount, balance-amount);
		
	}
	
	static void findAccount(int accountNumber)throws InvalidAccountException{
		if(accountNumber < 5001 || accountNumber > 5005) {
			throw new InvalidAccountException(accountNumber);
		}
		System.out.println("account "+accountNumber + "found!");
	}
	
	static void validateAmount(double amount,String operation) throws InvalidAmountException{
		if(amount <= 0 || amount > 100000) {
			throw new InvalidAmountException(amount,operation);
		}
		System.out.println("amount $ " +amount + "is valid for " + operation);
	}
	
	static void performOperationOnClosedAccount(int accountNumber,String operation)throws AccountClosedEception {
		boolean isClosed = true;
		if(isClosed) {
			throw new AccountClosedEception(accountNumber, operation);
		}
		System.out.println("operation completed!");
		
	}
	
	static void processTransaction(int accountNumber, double amount, boolean isActive) {
		System.out.println("\n------processing transaction ------");
		
		System.out.println("account: "+accountNumber + "|amount:$"+amount);
		
		try {
			if(accountNumber<5001||accountNumber>5005) {
				throw new InvalidAccountException(accountNumber);
			}
			
			if(!isActive) {
				throw new AccountClosedEception(accountNumber,"transaction");
			}
			
			if(amount <= 0 || amount > 1000000) {
				throw new InvalidAmountException(amount, "transaction");
			}
			double balance = 5000;
			if(amount > balance) {
				throw new InsufficientFundsException(amount, balance);
			}
			
			System.out.println("transaction sucessful!");
			System.out.printf("proceed $%,.2f for account %d%n",amount, accountNumber);
			
		}catch (InvalidAccountException e) {
			// TODO: handle exception
			System.out.println("x "+e.getMessage());
		}catch (AccountClosedEception e) {
			// TODO: handle exception
			System.out.println("x" + e.getMessage());
		}catch (InvalidAmountException e) {
			// TODO: handle exception
			System.out.println("x" + e.getMessage());
		}catch (InsufficientFundsException e) {
			// TODO: handle exception
			System.out.println("x"+e.getMessage());
			System.out.printf("you need $%,.2f more .%n", e.getShortage());
		}
	}
	
}