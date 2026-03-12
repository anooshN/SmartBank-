public class Loan{
	private static int nextLoadId = 1;

	private int loanId;
	private int accountNumber;
	private double loanAmount;
	private double interestRate;
	private int termMonths;
	private double amountPaid;
	private String status;
	public Loan(int accountNumber, double loanAmount, double interestRate, int termMonths) {
		super();
		this.accountNumber = accountNumber;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.termMonths = termMonths;
		this.loanId =nextLoadId++;
		this.amountPaid=0.0;
		this.status="pending";
		
	}
	public static int getNextLoadId() {
		return nextLoadId;
	}
	public int getLoanId() {
		return loanId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public int getTermMonths() {
		return termMonths;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public String getStatus() {
		return status;
	}
	
	public double getTotalAmount() {
		return loanAmount + (loanAmount * interestRate * termMonths / 12);
	}
	
	public double getRemainingAmount() {
		return getTotalAmount()-amountPaid;
	}
	
	public double getMonthlyPayment() {
		return getTotalAmount()/termMonths;
	}
	
	public void reject() {
		this.status = "rejected";
	}
	
	public boolean makePayment(double amount) {
		if(amount <= 0) {
			System.out.println("payment amount must be positive!");
			return false;
		}
		
		if(amount>getRemainingAmount()) {
			System.out.println("payment amount exceeds remaining balance!");
			System.out.printf("remaining: $%.2f%n", getRemainingAmount());
			return false;
		}
		amountPaid+=amount;
		
		if(getRemainingAmount() <= 0.01) {
			status = "paid";
		}
		return true;
	}
	
	  public void display() {
	        System.out.println("\n╔════════════════════════════════════════╗");
	        System.out.println("║            LOAN DETAILS                ║");
	        System.out.println("╚════════════════════════════════════════╝");
	        System.out.println("Loan ID: " + loanId);
	        System.out.println("Account Number: " + accountNumber);
	        System.out.printf("Loan Amount: $%.2f%n", loanAmount);
	        System.out.printf("Interest Rate: %.2f%%%n", interestRate * 100);
	        System.out.println("Term: " + termMonths + " months");
	        System.out.printf("Total Amount: $%.2f%n", getTotalAmount());
	        System.out.printf("Amount Paid: $%.2f%n", amountPaid);
	        System.out.printf("Remaining: $%.2f%n", getRemainingAmount());
	        System.out.printf("Monthly Payment: $%.2f%n", getMonthlyPayment());
	        System.out.println("Status: " + status);
	    }
	
	  public String toFileString() {
	        return loanId + "," + accountNumber + "," + loanAmount + "," + interestRate + "," + 
	               termMonths + "," + amountPaid + "," + status;
	    }
	    
	  public static Loan fromFileString(String line) {
		  try {
			  String[] parts=line.split(", ");
			  
			  int loanId=Integer.parseInt(parts[0].trim());
			  int accNum=Integer.parseInt(parts[1].trim());
			  double loanAmt=Double.parseDouble(parts[2].trim());
			  double rate=Double.parseDouble(parts[3].trim());
			  int term=Integer.parseInt(parts[4].trim());
			  double paid=Double.parseDouble(parts[5].trim());
			  String status=parts[6].trim();
			  
			  Loan loan=new Loan(accNum, loanAmt, rate, term);
			  loan.loanAmount=paid;
			  loan.loanId=loanId;
			  loan.status=status;
			  
			  if(loanId >= nextLoadId) {
				  nextLoadId = loanId+1;
				  
			  }
			  
			  return loan;
			  
			  
			  
			  
			  
		  }catch (Exception e) {
			// TODO: handle exception
			  System.out.println("error parsing loan:"+line);
			  return null;
		}
	  }
	
	
}