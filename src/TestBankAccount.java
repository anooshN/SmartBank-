public class TestBankAccount{
	public static void main(String args[]) {
		System.out.println("========== Testing Bankaccount class =======");
		
		Customer customer1 = new Customer("anoosh", 1001,"anoosh@gmail.com", "000-000-0000");
		BankAccount account1 = new BankAccount(5001, "savings", 10000, customer1);

		account1.displayAccountInfo();
		
		System.out.println("==== Test: Deposit ===");
		account1.deposit(5000);
		
		System.out.println();
		
		System.out.println("=== test: insufficient Funds ====");
		account1.withdraw(50000);
		
		System.out.println();
		
		Customer customer2 = new Customer("john", 1002, "john@smartbank.com", "000-000-0000");
		BankAccount account2=new BankAccount(5002,"checking",2000,customer2);
		
		System.out.println("==== Test: transfer ===");
		account1.transfer(account2, 4000);
		
		System.out.println("\n=== balance after transfer ====");
		System.out.printf("account 1 balance: $%, .2f%n", account1.getBalance());
		System.out.printf("account 2 balance: $%, .2f%n",account2.getBalance());
		
		System.out.println();
		
		System.out.println("===== test : apply interest====");
		account1.applyInterest(0.05,1);
		
		System.out.println();
		
		System.out.println("==== test loan eligibility====");
		boolean eligible = account1.isEligibleforLoan(20000);
		System.out.println("eligible for $20000 loan? "+(eligible ? "yes" : "no"));
		System.out.println();
		
		System.out.println("====== test: invalid deposit ====");
		account1.deposit(-500);
		
		System.out.println();
		
		System.out.println("=== test: close account ====");
		BankAccount account3 = new BankAccount(5003, "savings", 0 , customer1);
		account3.closeAccount();
		
		System.out.println();
		
		System.out.println("==== test: use closed account ====");
		account3.deposit(1000);
		
		
		System.out.println();
		
		System.out.println("====== final account states ======");
		account1.displayAccountInfo();
		account2.displayAccountInfo();
		
		
		
	}
}