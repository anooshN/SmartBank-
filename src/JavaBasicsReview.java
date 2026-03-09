import java.util.Scanner;

public class JavaBasicsReview{
	public static void main(String[] argss) {
		
		System.out.println("===== java basics review ======");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=====challenge 1: variables & datatypes =====");
		
		String customerNameString = "John Doe";
		int accountNumberLong = 12345;
		double balance = 50000.50;
		boolean isActive = true;
		double interestRate = 0.05;
		
		System.out.println("customer : " +customerNameString);
		System.out.println("account Number: " + accountNumberLong);
		System.out.println("balance :"+balance);
		System.out.println("active : "+isActive);
		System.out.println("interest rate : "+(interestRate*100)+"%");
		
		System.out.println("\n challenge 2: operators");
		
		int balance1=1000;
		int balance2 = 500;
		
		int total = balance1 + balance2;
		int difference = balance1-balance2;
		int avg = total/2;
		boolean is=balance1 > balance2;
		boolean gr = ((balance1 * 2) == balance2) ? true : false;
		
		System.out.println("\n ==== challenge 3: conditionals-----");
		
		int accountBalance = 7500;
		
		if(balance >= 10000) {
			System.out.println("premium account");
		}else if(balance >= 5000) {
			System.out.println("Gold account");
		}else if (balance >= 1000) {
			System.out.println("silver account");
		}else {
			System.out.println("basic account");
		}
		
		
		System.out.println("\n === challenge 4: switch statement====");
		
		String ch = "savings";
		
		switch (ch) {
	    case "savings":
	        System.out.println("3.5%");
	        break;
	    case "checking":
	        System.out.println("1.0%");
	        break;
	    case "fixed_deposit":
	        System.out.println("6.5%");
	        break;
	    default:
	        System.out.println("invalid choice");
	}
		System.out.println("\n ------ challenge 5: for loop===");
		
		for(int i = 5001 ; i <= 5010 ; i++) {
			System.out.println(" account : "+i);
		}
	
		System.out.println("\n --------- challenge 6: while loop-");
		
		double balance3=1000;
		
		int years = 1;
		
		while(years <= 5) {
			balance3 = (balance3 * 1.05);
			System.out.println("year" +years+ ";" +balance3);
			years++;
		}
		
		System.out.println("\n----challenge 7: enhanced for loop -----");
		
		String[] customer = {"alice", "bob", "charle","david","emma"};
		
		for (String string : customer) {
			int i = 1;
			System.out.println("customer "+i + string );
			i++;
		}
		
		
		System.out.println("\n--- challenge 8: Nested loops----");
		
		int account = 1001;
		for(int i = 1; i<=5;i++) {
			for(int j = 1; j<=5;j++) {
				System.out.print(account + " ");
				account++;
			}
			System.out.println();
		}
		
		System.out.println("\n--- challenge 9: user input----");
		
		Scanner scanner2= new Scanner(System.in);
		double balance4 = 5000;
		
		double withdrawl;
		
		do {
			System.out.println("enter withdrawl amount:");
			withdrawl = scanner2.nextDouble();
		}while(withdrawl<=0);
		
		if(withdrawl <= balance4) {
			balance4-=withdrawl;
			System.out.println("withdrawl successful, remaining balance:"+balance);
		}else {
			System.out.println("insuffient funds");
		}
		
		System.out.println("\n --- challenge 10: atm simulation");
		
		double amount = 1000;
		int choice =0;
		while(choice != 4) {
			System.out.println("1.check balance \n 2. deposit \n 3.withdrawl \n 4. exit");
			System.out.println("enter choice:");
		
			if(!scanner2.hasNextInt()) {
				System.out.println("invalid input, enter number");
				scanner2.next();
				continue;
			}
			
		
			choice = scanner2.nextInt();
			switch (choice) {
			case 1: {
				
				System.out.println("current balance: "+balance);
				break;
			}
			case 2:{
				System.out.println("enter deposit number:");
				double deposit = scanner2.nextDouble();
				if(deposit >0) {
					amount += balance;
					System.out.println("deposit succesful.  new balance :"+balance);
				}else {
					System.out.println("invalid deposit amount");
				}
				break;
			}
			case 3:{
				System.out.println("enter withdrawl amount:");
				double withdrawl1 = scanner2.nextDouble();
				if(withdrawl1 > amount) {
					System.out.println("insufficent balance:");
				}else {
					account -= withdrawl1;
					System.out.println("transaction sucessful: available balance:"+ amount);
				}
				break;
			}
			case 4:{
				System.out.println("thank u for using");
				break;
			}
			default:
				System.out.println("select right number");
			}
		}
		
		
		scanner.close();
		scanner2.close();
		System.out.println("\n review complete");
	}
}