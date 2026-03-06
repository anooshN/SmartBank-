import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArrayListDemo{
	public static void main(String[] args) {
		System.out.println("========= ArrayList Demo =======");
		
		System.out.println("==== part 1: String arrayList =====");
		
		ArrayList<String> customerNames = new ArrayList<>();
		
		customerNames.add("anoosh");
		customerNames.add("John");
		customerNames.add("Sarah");
		customerNames.add("Mike");
		customerNames.add("emma");
		
		System.out.println("All customers:");
		
		for(String name : customerNames) {
			System.out.println("===" + name);
		}
		
		System.out.println("first customer :" + customerNames.get(0));
		System.out.println("third customer : "+customerNames.get(2));
		
		boolean hasJohn = customerNames.contains("John");
		System.out.println("\nHas John? "+ hasJohn);
		
		int sarahIndex = customerNames.indexOf("Sarah");
		System.out.println("Sarah is at index: "+sarahIndex);
		
		System.out.println("\n Removing customer at index 1...");
		customerNames.remove(1);
		
		System.out.println("after remove1:");
		for(String name :customerNames ) {
			System.out.println("== "+ name);
		}
		
		System.out.println("\nRemoving 'Mike'...");
		customerNames.remove("Mike");
		
		System.out.println("After removal:");
		for(String name : customerNames) {
			System.out.println("==" +name);
		}
		
		System.out.println("\n adding 'david' AtomicBoolean positionAtomicBoolean 11...");
		customerNames.add(1,"David");
		
		
		System.out.println("After insertion:");
		for(String name:customerNames) {
			System.out.println(" --- "+name);
		}
		
		System.out.println("\n\n --- part 2: Integer ArrayList --");
		
		ArrayList<Integer> accountBalances = new ArrayList<Integer>();
		
		accountBalances.add(5000);
		accountBalances.add(12000);
		accountBalances.add(7500);
		accountBalances.add(3000);
		accountBalances.add(15000);
		
		System.out.println("all balances:");
		for(Integer balance : accountBalances) {
			System.out.println("$" +balance);
		}
		
		int total = 0;
		
		for(Integer balance : accountBalances) {
			total += balance;
		}
		
		System.out.println("\n Total : $ " +total);
		
		int max = accountBalances.get(0);
		for(Integer balance : accountBalances) {
			if(balance > max) {
				max = balance;
			}
		}
		
		System.out.println("Highest balance : $" +max);
		
		int min = accountBalances.get(0);
		for(Integer balance : accountBalances) {
			if(balance < min) {
				min = balance;
			}
		}
		
		System.out.println("Lowest balances : $" +min);
		int count = 0;
		for(Integer balance: accountBalances) {
			if(balance > 5000) {
				count++;
			}
		}
		
		System.out.println("Accounts with balance > $5000: " + count);
        
        // ============================================
        // PART 3: ArrayList with Objects (Customer)
        // ============================================
        System.out.println("\n\n--- Part 3: Customer Objects ArrayList ---");
        
        ArrayList<Customer> customers = new ArrayList<Customer>();
        
        customers.add(new Customer("Anoosh", 1001, "anoosh@bank.com", "470-222-4837"));
        customers.add(new Customer("John", 1002, "john@bank.com", "555-123-4567"));
        customers.add(new Customer("Sarah", 1003, "sarah@bank.com", "555-987-6543"));
        
        System.out.println("All customers:");
        for (Customer customer : customers) {
            System.out.println("\nCustomer ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
        }
        
        System.out.println("\n ----- finding customer by id ----");
        
        int searchId = 1002;
        Customer foundCustomer = null;
        
        for(Customer customer: customers) {
        		if(customer.getCustomerId() == searchId) {
        			foundCustomer = customer;
        			break;
        		}
        }
        
        if(foundCustomer != null) {
        		System.out.println("found : "+foundCustomer.getName());
        }else {
        		System.out.println("Customer not found!");
        }
        
        
        System.out.println("\n\n --- 4: why arraylist is better-----");
        
        String[] arrayNames = new String[3];
        arrayNames[0] = "A";
        arrayNames[1] = "b";
        arrayNames[2] = "c";
        System.out.println("array size:"+arrayNames.length + "Fixed");
		
        ArrayList<String> listNames = new ArrayList<String>();
        listNames.add("A");
        listNames.add("B");
        listNames.add("C");
        listNames.add("D");  // ✅ Works! No size limit
        listNames.add("E");
        listNames.add("F");
        
        System.out.println("ArrayList size: " + listNames.size() + " (DYNAMIC!)");
        
System.out.println("\n\n--- Part 5: Useful Methods ---");
        
        ArrayList<String> cities = new ArrayList<>();
        cities.add("Atlanta");
        cities.add("New York");
        cities.add("Chicago");
        cities.add("Los Angeles");
        
        // isEmpty
        System.out.println("Is empty? " + cities.isEmpty());  // false
        
        // clear
        ArrayList<String> temp = new ArrayList<>();
        temp.add("test");
        System.out.println("Temp size before clear: " + temp.size());
        temp.clear();
        System.out.println("Temp size after clear: " + temp.size());
        System.out.println("Is empty now? " + temp.isEmpty());  // true
        
        // set (replace at index)
        System.out.println("\nBefore set: " + cities.get(0));
        cities.set(0, "Dallas");  // Replace Atlanta with Dallas
        System.out.println("After set: " + cities.get(0));
        
        System.out.println("\nFinal cities list:");
        for (String city : cities) {
            System.out.println("- " + city);
        }
        
        System.out.println("\n===== ARRAYLIST DEMO COMPLETE =====");
	}
}