import java.awt.desktop.AboutHandler;
import java.time.chrono.IsoChronology;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArrayMethodsReview{
	public static void main(String[] ars) {
		System.out.println("======== arrays & methods review =====\n");
		
		System.out.println("--------- challenge 1: array basics -----");
		
		int[] acc_bal = {1000,2500,3000,4000};
		
		for(int i =0; i < acc_bal.length;i++) {
			System.out.println("index:"+ i + "  " + acc_bal[i]);
		}
		
		System.out.println("\n---- challenge 2: find maximum balance -----");
		
		int hig_bal = acc_bal[0];
		for(int i =0; i<acc_bal.length;i++) {
			if(acc_bal[i] > hig_bal) {
				hig_bal = acc_bal[i];
			}
		}
		System.out.println("highest balance :" + hig_bal);
		
		System.out.println("\n --- challenge 3: calculate average----");
		
		double avg =0;
		for(int i =0; i<acc_bal.length;i++) {
			avg += acc_bal[i];
		}
		double avg1 = avg/2;
		System.out.printf("avg balance : %.2f\n" ,avg1);
		
		
		
		System.out.println("\n ---- challenge 4: count high balances---");
		
		int count =0;
		for(int i =0; i<acc_bal.length;i++) {
			if(acc_bal[i] > 2000) {
				count++;
			}
		}
		System.out.println("accounts more than 2000: "+ count);
		
		System.out.println("\n challenge 5: method - calculate interest ----");
		
		double result = calculateInterest(1000,0.05,3);
		System.out.println("interest : " + result);
		
		System.out.println("\n--- challenge 6: method - loan eligibility ----");
		boolean score = isEligibleForLoan(700,70000,true);
		System.out.println(" Eligible for score : "+score);
		
		System.out.println("\n--- challenge 7: method with array ----");
		
		
		
		System.out.println("\n ----- challenge 8: double all balances-----");
		
		doubleBalances(acc_bal);
		
		
		System.out.println("\n --- challenge 9: 2D array");
		
		int[][] accounts = {
				{5001,1000,25},
				{5002,2500,30},
				{5003,3000,35}
		};
		
		for(int i=0; i< accounts.length;i++) {
			for(int j=0;j<accounts.length;j++) {
				System.out.print(accounts[i][j]);
			}
			System.out.println();
		}
		
		
		System.out.println("\n ---- challenge 10: banking operations---");
		
		int[] accountNumbers = {5001,5002,5003,5004,5005};
		String[] customerNameStrings = {"alice", "bob","charle","david","emma"};
		double[] balances= {1000,2500,3000,1500,4000};
	
		printAllBalances(balances);
		int index=findAccountIndex(accountNumbers,5002); 
		System.out.println("account index : "+ index);
		
		deposit(balances, 0,200);
		boolean success=transfer(balances,0,2,200);
		System.out.println("transfer sucess:"+success);
		
		
	}
		
	public static double calculateInterest(double balance,double rate,int years) {
		
		double sim_int = balance * rate*years;
		return sim_int;
		
	}
	
	public static boolean isEligibleForLoan(int creditScore, double balance, boolean isEmployed) {
		return creditScore >=650 && balance >=5000 && isEmployed;
	}
	
	public static void printAllBalances(double[] balances) {
		for(int i=0;i<balances.length;i++) {
			System.out.println("array :"+balances[i]);
		}
	}
	
	public static void doubleBalances(int[] balances) {
		for(int i=0;i<balances.length;i++) {
			double bal = balances[i];
			System.out.println("Double Balances :"+ bal * 2);
		}
	}
	
	public static int findAccountIndex(int[] accountNumbers,int searchNumber) {
		for (int i : accountNumbers) {
			if(searchNumber == i) {
				return 1;
			}
			
		}
		return -1;
	}
	
	public static void deposit(double[] balances, int index, double amount) {
		if(amount > 0) {
			balances[index]+=amount;
			System.out.println("amount depoisted: ");
		}
	}
	
	public static boolean transfer(double[] balances, int fromIndex,int toIndex,double amount) {
		if(amount > 0 && balances[fromIndex] >= amount) {
			balances[fromIndex] -=amount;
			balances[toIndex] +=amount;
			
			return true;
		}
		return false;
		
	}
}