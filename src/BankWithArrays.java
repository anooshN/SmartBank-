import java.util.Iterator;
import java.util.Scanner;

public class BankWithArrays{
	
	static String[] customerNames = new String[5];
	static int[] accountNumbers = new int[5];
	static double[] balances = new double[5];
	
	public static void initializeData() {
		customerNames[0] = "Anoosh";
        accountNumbers[0] = 1001;
        balances[0] = 5000;
        
        customerNames[1] = "John";
        accountNumbers[1] = 1002;
        balances[1] = 7500;
        
        customerNames[2] = "Ah";
        accountNumbers[2] = 1003;
        balances[2] = 8000;
        
        customerNames[3] = "Jhn";
        accountNumbers[3] = 1004;
        balances[3] = 500;
        
        customerNames[4] = "gh";
        accountNumbers[4] = 1005;
        balances[4] = 8500;
	}
	
	 // Method 2: Display all customers
    public static void displayAllCustomers() {
        // Loop through arrays and print all customer info
    	
    		for(int i = 0; i < 5; i++) {
    			System.out.printf("%d. %-10s | Account: %d | Balance: $%.2f\n",i+1,customerNames[i],accountNumbers[i],balances[i]);
    		}
        // Format:
        // 1. Anoosh | Account: 1001 | Balance: $5000.00
        // 2. John   | Account: 1002 | Balance: $7500.00
        // etc.
    	
    }
    
    
    // Method 3: Find customer by account number
    // Return the index (0-4) if found, or -1 if not found
    public static int findCustomer(int accountNumber) {
        // Loop through accountNumbers array
        // If found, return the index
        // If not found, return -1
    	int index =0;
    		for (int i : accountNumbers) {
				if(i == accountNumber) {
					return index ;
				}
				index++;
			}
        return -1; // Change this!
    }
    
    
    // Method 4: Deposit money
    public static void deposit(int accountNumber, double amount) {
        // Use findCustomer() to get the index
    	    int index = findCustomer(accountNumber);
    	    
				if(index == -1) {
					System.out.println("Account not found");
					
				}else {
					 balances[index] += amount  ;
						System.out.println("Money deposited. new balance :"+balances[index] +customerNames[index]+index);
				}
			
        // If found:
        //   - Add amount to balances[index]
        //   - Print success message with new balance
        // If not found:
        //   - Print "Account not found"
    }
    
    
    // Method 5: Withdraw money
    public static void withdraw(int accountNumber, double amount) {
        // Use findCustomer() to get the index
    	int index = findCustomer(accountNumber);
	    
			if(index == -1) {
				System.out.println("Account not found");
			}else {
				balances[index] -= amount;
				System.out.printf("Money withdraw  new balance :"+balances[index] +  customerNames[index]);
				
			}
	
        // If found:
        //   - Check if balances[index] >= amount
        //   - If yes: subtract amount and print new balance
        //   - If no: print "Insufficient funds"
        // If not found:
        //   - Print "Account not found"
    }
    
    
    // Method 6: Check balance
    public static void checkBalance(int accountNumber) {
        // Use findCustomer() to get the index
    		int index = findCustomer(accountNumber);
    		
    			if(true) {
    				System.out.println("Customer Name : "+customerNames[index]+" \n Balance :"+ balances[index]);
    			}else {
    				System.out.println("Account nor found!");
    			}
    		}
    
    
    // Method 7: Calculate total bank balance
    public static double getTotalBankBalance() {
        // Loop through balances array
    		double Total_Bal = 0;
    		for (double i : balances) {
				Total_Bal+=i;
			}
        // Add all balances together
        // Return the total
        return Total_Bal; // Change this!
    }
    
    
    // Method 8: Find richest customer
    public static void findRichestCustomer() {
        // Loop through balances array
    		double High_Bal = balances[0];
    		int index = 0 ;
    		for(int i=1; i < balances.length;i++) {
    			if(balances[i] > High_Bal) {
    				High_Bal = balances[i];
    				index = i;
    			}
    			
    		}
    		
    		System.out.println("Customer Name : "+customerNames[index]+"\n Balances : "+balances[index] +"\n"+ index);
        // Find the highest balance
        // Print that customer's name and balance
    }
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		initializeData();
		
		System.out.println("=======SmartBank System ========");
		
		boolean running = true;
		
		while(running) {
			System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Display All Customers");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Show Total Bank Balance");
            System.out.println("6. Find Richest Customer");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            
            if(choice == 1) {
            		displayAllCustomers();
            }else if (choice == 2) {
				System.out.println("Enter Account Number");
				int Acc_Num = scanner.nextInt();
				checkBalance(Acc_Num);
			}else if (choice == 3) {
				System.out.println("Enter Account Number");
				int Acc_num = scanner.nextInt();
				System.out.println("Enter Amount To Deposit");
				double Dep = scanner.nextDouble();
				deposit(Acc_num, Dep);
			}else if(choice == 4) {
				System.out.println("Enter Account Number");
				int Acc_num = scanner.nextInt();
				System.out.println("Enter Amount To withdraw");
				double WIDR = scanner.nextDouble();
				withdraw(Acc_num, WIDR);
			}else if(choice == 5) {
				double Total = getTotalBankBalance();
				System.out.println("Total Bank Balance :"+Total);
			}else if(choice == 6) {
				findRichestCustomer();
			}else if(choice == 7) {
				System.out.println("thank you for using SmartBank");
				running = false;
			}else {
				System.out.println("Invalid choice!");
			}
		}
		scanner.close();
		 
	}
}