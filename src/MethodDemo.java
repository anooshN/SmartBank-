public class MethodDemo{
	 // Method 1: No parameters, no return
    // Create: public static void displayWelcomeMessage()
    // Should print: "Welcome to SmartBank!"
	public static void displayWelcomeMessage(){
		System.out.println("Welcome to SmartBanck");
	}
    
    // Method 2: With parameters, no return
    // Create: public static void displayAccountInfo(String name, int accountNumber, double balance)
    // Should print the customer's info nicely formatted
    
	public static void displayAccountInfo(String name, int accountNumber, double balance) {
		System.out.printf("Account Holder : %s \n Account Number : %d \n Account Number : %f ",name,accountNumber,balance);
	}
	  
    
    // Method 3: No parameters, with return
    // Create: public static double getDefaultInterestRate()
    // Should return: 0.05 (5%)
    
	public static double getDefaultInterestRate(){
		
		return 0.05;
	}
    
    // Method 4: With parameters, with return
    // Create: public static double calculateInterest(double balance, double rate, int years)
    // Formula: interest = balance * rate * years
    // Return the calculated interest
	public static double calculateInterest(double balance, double rate, int years) {
		double interest = balance * rate * years;
		return interest;
	}
	 
	
    
    
    // Method 5: Boolean return (eligibility check)
    // Create: public static boolean isEligibleForLoan(int creditScore, double balance, boolean isEmployed)
    // Rules: creditScore >= 650 AND balance >= 5000 AND isEmployed == true
    // Return true if eligible, false otherwise
	
	public static boolean isEligibleForLoan(int creditScore, double balance, boolean isEmployed) {
		if(creditScore >= 650 && balance >= 5000 && isEmployed == true) {
			return true;
		}else {
			return false;
		}
	}
	   
    
	

	
	public static void main(String[] args) {
		// Call all 5 methods here and test them!
	    
	    // Test Method 1
	    displayWelcomeMessage();
	    
	    // Test Method 2
	    displayAccountInfo("Anoosh", 1001, 10000);
	    
	    // Test Method 3
	    double rate = getDefaultInterestRate();
	    System.out.println("Interest Rate: " + rate);
	    
	    // Test Method 4
	    double interest = calculateInterest(10000, 0.05, 3);
	    System.out.println("Interest earned: $" + interest);
	    
	    // Test Method 5
	    boolean eligible = isEligibleForLoan(700, 6000, true);
	    if (eligible) {
	      System.out.println("Loan approved!");
	    } else {
	    		System.out.println("Loan Declained!");
	    }

		
	}
}