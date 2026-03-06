import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo{
	public static void main(String[] args) {
		System.out.println("====== HashMap Demo ======");
		
		System.out.println("--------part 1: Basic Operation ---");
		
		HashMap<Integer, String> accountToName = new HashMap<Integer, String>();
		
		accountToName.put(1001, "Anoosh");
		accountToName.put(1002, "john");
		accountToName.put(1003, "sarah");
		accountToName.put(1004, "mike");
		accountToName.put(1005, "emma");
		
		String name = accountToName.get(1003);
		System.out.println("account 1003 belongs to : " +name);
		
		System.out.println("total accounts : "+accountToName.size());
		
		boolean hasMike = accountToName.containsValue("Mike");
		System.out.println("Has customer Mike?"+hasMike);
		
		System.out.println("\n updating account 1001....");
		accountToName.put(1001, "Anoosh N");
		System.out.println("account 1001 now:"+accountToName.get(1001));
		
		System.out.println("\n removing account 1004...");
		accountToName.remove(1004);
		System.out.println("account 1004 exists? "+accountToName.containsKey(1004));
		
		System.out.println("\n\n---part 2 : looping through HashMap---");
		
		System.out.println("\n method 1: using keyset()");
		Set<Integer> keySet=accountToName.keySet();
		for(Integer accountNum:keySet) {
			String customerName = accountToName.get(accountNum);
			System.out.println("account "+accountNum + "->" +customerName);
		}
		
		System.out.println("\nmethod 2: using entrySet()");
		for(Map.Entry<Integer, String> entry : accountToName.entrySet()) {
			Integer keyInteger = entry.getKey();
			String valueString = entry.getValue();
			System.out.println("account "+keyInteger +"->"+valueString);
		}
		
		System.out.println("\n method 3 : using values()");
		for(String customerString : accountToName.values()) {
			System.out.println("customer : "+customerString);
		}
		
		
		
		System.out.println("\n\n -----part3 : account number -> balance ----");
		
		HashMap<Integer, Double> accountBalanceHashMap=new HashMap<Integer, Double>();
		
		accountBalanceHashMap.put(1001, 5000.0);
		accountBalanceHashMap.put(1002, 12000.0);
		accountBalanceHashMap.put(1003, 7500.0);
		accountBalanceHashMap.put(1004, 15000.0);
		
		
		int searchAmount=1002;
		if(accountBalanceHashMap.containsKey(searchAmount)) {
			double Balance = accountBalanceHashMap.get(searchAmount);
				System.out.printf("account %d balance: $%,.2f%n",searchAmount,Balance);
			}else {
				System.out.println("account not found!");
				
			
			}
		
		double total =0;
		for(Double balance : accountBalanceHashMap.values()) {
			total+=balance;
		}
		System.out.printf("\n total bank balance : $%,.2f%n",total);
		
		int richestamount=0;
		double highestbalance=0;
		
		for(Map.Entry<Integer, Double> entry : accountBalanceHashMap.entrySet()) {
			if(entry.getValue()>highestbalance) {
				highestbalance = entry.getValue();
				richestamount = entry.getKey();
			}
		}
		System.out.printf("\nrichest account: %d with balance $%,.2f%n", richestamount,highestbalance);
		System.out.println("\n\n --- part4: account Number -> bankaccount object---");
		
		HashMap<Integer, BankAccount> accounts= new HashMap<Integer, BankAccount>();
		
		// Create customers
        Customer c1 = new Customer("Anoosh", 1001, "anoosh@bank.com", "470-222-4837");
        Customer c2 = new Customer("John", 1002, "john@bank.com", "555-123-4567");
        Customer c3 = new Customer("Sarah", 1003, "sarah@bank.com", "555-987-6543");
        
        // Create accounts
        BankAccount acc1 = new BankAccount(5001, "SAVINGS", 10000, c1);
        BankAccount acc2 = new BankAccount(5002, "CHECKING", 5000, c2);
        BankAccount acc3 = new BankAccount(5003, "SAVINGS", 15000, c3);
        
        // Put in HashMap using account number as key
        accounts.put(5001, acc1);
        accounts.put(5002, acc2);
        accounts.put(5003, acc3);
	
        int lookupAccount = 5002;
        BankAccount foundAccount=accounts.get(lookupAccount);
        
        if(foundAccount!=null) {
        		System.out.println("\n account found!");
        		System.out.println("account number: " +foundAccount.getAccountNumber());
        		System.out.println("customer : "+foundAccount.getCustomer().getName());
        		System.out.printf("balance: $%,.2f%n" , foundAccount.getBalance());
        }else {
        	System.out.println("account not found!");
        }
        
        System.out.println("\n depositing $2000 to account "+ lookupAccount + "...");
        foundAccount.deposit(2000);
        
        System.out.println("\n---- all bank accounts ----");
        for(Map.Entry<Integer	, BankAccount> entry : accounts.entrySet()) {
        	BankAccount account= entry.getValue();
        	System.out.printf("\nAccount: %d | Customer: %s | Balance: $%,.2f%n",
                    account.getAccountNumber(),
                    account.getCustomer().getName(),
                    account.getBalance());
        }
	
        System.out.println("\n\n--- part 5: hashmap vs arraylist  speeptest ---");
        
        ArrayList<BankAccount> accountList= new ArrayList<>();
        
        accountList.add(acc1);
        accountList.add(acc2);
        accountList.add(acc3);
        
        System.out.println("\n arrayList finding account 5002:");
        long startTime=System.nanoTime();
        BankAccount foundIntListAccount=null;
        for (BankAccount acc : accountList) {
            if (acc.getAccountNumber() == 5002) {
                foundIntListAccount = acc;
                break;
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Time: " + (endTime - startTime) + " nanoseconds");
        
        // HashMap approach - instant lookup
        System.out.println("\nHashMap - Finding account 5002:");
        startTime = System.nanoTime();
        BankAccount foundInMap = accounts.get(5002);
        endTime = System.nanoTime();
        System.out.println("Time: " + (endTime - startTime) + " nanoseconds");
        
        System.out.println("\n✓ HashMap is MUCH faster for lookups!");
        
        // ============================================
        // PART 6: getOrDefault - Safe Retrieval
        // ============================================
        System.out.println("\n\n--- Part 6: Safe Retrieval with getOrDefault ---");
        
        HashMap<String, Long> phoneBook = new HashMap<String,Long>();
        phoneBook.put("Anoosh", 4702224837L);
        phoneBook.put("John", 5551234567L);
        
        // Regular get - returns null if key doesn't exist
        Long phone1 = phoneBook.get("Anoosh");
        System.out.println("Anoosh's phone: " + phone1);
        
        Long phone2 = phoneBook.get("Mike");  // Mike doesn't exist
        System.out.println("Mike's phone: " + phone2);  // Prints: null
        
        // getOrDefault - returns default value if key doesn't exist
        Long phone3 = phoneBook.getOrDefault("Mike", 0L);
        System.out.println("Mike's phone (with default): " + phone3);  // Prints: 0
        
        // ============================================
        // PART 7: Practical Banking Example
        // ============================================
        System.out.println("\n\n--- Part 7: Real Banking Scenario ---");
        
        HashMap<Integer, BankAccount> bank = new HashMap<>();
        
        // Add multiple accounts
        bank.put(5001, new BankAccount(5001, "SAVINGS", 10000, 
                new Customer("Alice", 1001, "alice@bank.com", "555-0001")));
        bank.put(5002, new BankAccount(5002, "CHECKING", 5000, 
                new Customer("Bob", 1002, "bob@bank.com", "555-0002")));
        bank.put(5003, new BankAccount(5003, "SAVINGS", 15000, 
                new Customer("Charlie", 1003, "charlie@bank.com", "555-0003")));
        
        System.out.println("Bank initialized with " + bank.size() + " accounts");
        
        // Transfer between accounts
        System.out.println("\n--- Transfer Operation ---");
        int fromAcc = 5001;
        int toAcc = 5002;
        double amount = 2000;
        
        if (bank.containsKey(fromAcc) && bank.containsKey(toAcc)) {
            BankAccount from = bank.get(fromAcc);
            BankAccount to = bank.get(toAcc);
            
            System.out.println("Transferring $" + amount + " from " + fromAcc + " to " + toAcc);
            boolean success = from.transfer(to, amount);
            
            if (success) {
                System.out.println("\n✓ Transfer completed!");
            }
        } else {
            System.out.println("One or both accounts not found!");
        }
        
        System.out.println("\n===== HASHMAP DEMO COMPLETE =====");
    
        
        
        
        
	}
}