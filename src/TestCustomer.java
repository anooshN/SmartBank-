public class TestCustomer{
	public static void main(String[] args) {
		System.out.println("==== Testing Customer Class ====\n");
		
		Customer customer1 = new Customer("anoosh",1001,"anoosh@smartbank.com","000-000-0000");
		customer1.displayInfo();
		System.out.println(customer1.greet());
		
		System.out.println("\n");
		
		Customer customer2 = new Customer();
		customer2.displayInfo();
		
		System.out.println("\n");
		
		customer2.setName("John Doe");
		customer2.setEmail("john@smartbank.com");
		customer2.setPhoneNumber("000-000-0000");
		System.out.println("After updating with setters:");
		customer2.displayInfo();
		
		
		System.out.println("\n");
		
		System.out.println("customer1 name : " +customer1.getName());
		System.out.println("customer1 id :" + customer1.getCustomerId());
		System.out.println("customer1 email : " + customer1.getEmail());
		
		System.out.println("\n");
		
		Customer customer3 = new Customer("Sarah", 1002, "sarah@smartbank.com", "555-987-6543");
		Customer customer4 = new Customer("Mike", 1003, "mike@smartbank.com", "555-456-7890");
        
		System.out.println("== all customers ==");
		customer1.displayInfo();
		System.out.println();
		customer3.displayInfo();
		System.out.println();
		customer4.displayInfo();
		
	}
}