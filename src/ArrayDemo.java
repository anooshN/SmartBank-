import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.OptionalInt;

public class ArrayDemo{
	public static void main(String[] args) {
		
		int[] account_balncess = new int[10];
		
		account_balncess[0] = 5000 ;
		account_balncess[1] = 12000;
		account_balncess[2] = 3000;
		account_balncess[3] = 8500;
		account_balncess[4] = 15000;
		account_balncess[5] = 2500;
		account_balncess[6] = 9000;
		account_balncess[7] = 6500;
		account_balncess[8] = 11000;
		account_balncess[9] = 4000;
		
		int i = 1;
		int bal = 0;
		
		for (int acc : account_balncess) {
			System.out.println("Account "+i+": "+acc );
			 bal += acc;
			 i++;
		}
		
		System.out.println("Total Balancess : "+ bal);
//		int max = Arrays.stream(account_balncess).max().getAsInt();
//		int min = Arrays.stream(account_balncess).min().getAsInt();
		
		double max = account_balncess[0];
		double min = account_balncess[0];
		
		for (int value : account_balncess) {
			if(value > max) max =value;
			if(value < min) min =value;
		}
		
		int j = 0;
		for(int acc:account_balncess) {
			if(acc > 5000) {
				j++;
			}
		}
		
		System.out.println("Highest Balance :" +max);
		System.out.println("Low Balance : " +min);
		System.out.println("Accounts have balance > 5000 : " + j);
		
		
     
        
	}
}