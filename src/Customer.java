public class Customer{
	private String name;
	private int customerId;
	private String email;
	private String phoneNumber;
	
	public Customer() {
		this.name = "Unknown";
		this.customerId = 0;
		this.email = "no-email@example.com";
		this.phoneNumber = "000-000-0000";
		
	}
	
	public Customer(String name,int customerId,String email, String phoneNumber) {
		this.name = name;
		this.customerId = customerId;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void displayInfo() {
		System.out.println("=== Customer Info ===");
		System.out.println("ID: " + customerId);
		System.out.println("Name: " + name);
		System.out.println("Email : " +email);
		System.out.println("phone : " +phoneNumber);
	}
	
	public String greet() {
		return "hello, " + name + "!Welcome to SmartBank!";
	}
}