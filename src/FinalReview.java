import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FinalReview{
	public static void main(String[] args) {
		System.out.println("===== final review Mixed concepts=====");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("----- challenge 1: arraylist-----");
		
		ArrayList<String> name= new ArrayList<>();
		
		name.add("alice");
		name.add("bob");
		name.add("charlie");
		
		for (String string : name) {
			System.out.println(string);
		}
		name.remove("bob");
		System.out.println("Size of name"+name.size());
		
		
		System.out.println("\n ---- challenge 2: hashmap _-------");
		
		HashMap<Integer, Double> acc_bal = new HashMap<Integer, Double>();
		
		acc_bal.put(5001, 1000.0);
		acc_bal.put(5002, 2500.0);
		acc_bal.put(5003, 3000.0);
		
		acc_bal.get(5002);
		acc_bal.put(5001, 1500.0);
		
		for (Integer acc : acc_bal.keySet()) {
			System.out.println("account : "+acc+ " balance: " + acc_bal.get(acc));
		}
		
		System.out.println("\n---- challenge 3: exception handling ---");
		
		try {
			System.out.println("enter first num: ");
			int a = scanner.nextInt();
			System.out.println("enter second num:");
			int b = scanner.nextInt();
			
			int c = a/b;
			System.out.println("divide:"+c);
		}catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("only enter number ");
		}catch (ArithmeticException e) {
			// TODO: handle exception
			System.out.println("not divide by zero");
		}
		
		System.out.println("\n==== chalenge 4: custom exception ----");
		try{
			withdrawMoney(1000, 5000);
		}catch(InsufficientFundsException e){
			System.out.println("insufficient funds :" + e.getMessage());
		}
		
		System.out.println("\n---- challenge 5: mini banking syatem ----");
		
		HashMap<Integer, BankAccount> accounts= new HashMap<Integer, BankAccount>();
		
		accounts.put(5001, new BankAccount(5001, 1000.0,"alice") );
		accounts.put(5002, new BankAccount(5002,2500.0, "bob"));
		accounts.put(5003, new BankAccount(5003, 3000.0,"charle"));
		
		
		
		
	}
	
	public static void withdrawMoney(double balance, double amount)throws InsufficientFundsException{
			
		if(amount > balance) {
			throw new InsufficientFundsException(amount, balance);
		}
		System.out.println("withdrawl succesful");
	}
}