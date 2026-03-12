import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction{
	public static int nextTransactionId = 1;
	
	private int transactionId;
	private int accountNumber;
	private String type;
	private double amount;
	private double balanceAfter;
	private LocalDateTime timestamp;
	private String description;
	public Transaction(int accountNumber, String type, double amount, double balanceAfter, String description) {
		super();
		this.accountNumber = accountNumber;
		this.type = type;
		this.amount = amount;
		this.balanceAfter = balanceAfter;
		this.description = description;
		this.transactionId = nextTransactionId++;
		this.timestamp = timestamp;
	}
	public static int getNextTransactionId() {
		return nextTransactionId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public String getType() {
		return type;
	}
	public double getAmount() {
		return amount;
	}
	public double getBalanceAfter() {
		return balanceAfter;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public String getDescription() {
		return description;
	}
	
	public void display() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 System.out.printf("%-8d %-15s %-15s $%-12.2f $%-12.2f %s%n",
		            transactionId,
		            type,
		            timestamp.format(formatter),
		            amount,
		            balanceAfter,
		            description);
		    }
	
		public String toFileString() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    
		    // Handle null timestamp
		    String timestampStr = (timestamp != null) ? timestamp.format(formatter) : LocalDateTime.now().format(formatter);
		    
		    return transactionId + "," + accountNumber + "," + type + "," + amount + "," + 
		           balanceAfter + "," + timestampStr + "," + description;	
		}
		
		public static Transaction fromFileString(String line) {
			try {
				String[] parts=line.split(", ");
				
				int txId = Integer.parseInt(parts[0].trim());
				int accNum = Integer.parseInt(parts[1].trim());
				String type = parts[2].trim();
				double amount=Double.parseDouble(parts[3].trim());
				double balanceAfter = Double.parseDouble(parts[4].trim());
				String timestampStr= parts[5].trim();
				String description = parts.length > 6 ? parts[6].trim(): " ";
				
				Transaction tx= new Transaction(accNum, type, amount, balanceAfter, description);
				tx.transactionId = txId;
				DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				tx.timestamp = LocalDateTime.parse(timestampStr,formatter);
				
				if(txId >= nextTransactionId) {
					nextTransactionId = txId +1;
				}
				
				return tx;	
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("error parsing transaction: "+line);
				return null;
			}
		}	
}