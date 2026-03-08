import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class ExceptionDemo{
	public static void main(String[] args) {
		System.out.println("===== exception handling demo=====\n");
		
		System.out.println("----- part 1; Division handling demo ----");
		
		System.out.println("\n without exception handling:");
		try {
			demonstrateCrash();
		}catch (ArithmeticException e) {
			
			// TODO: handle exception
			System.out.println("program would have crashed here!");
		}
		
		System.out.println("\n with exception handling:");
		safeDivision(10,0);
		System.out.println("program continues normally");
		
		System.out.println("\n-----part 2: array index out of bonds----");
		
		int[] balances =  {1000,2000,3000};
		
		System.out.println("\n unsafe array access:");
		try {
			System.out.println("accessing index 10...");
			int value = balances[10];
			System.out.println(value);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("x error: "+e.getMessage());
			System.out.println("array only has "+balances.length +"elements");
		}
		
		System.out.println("\nsafe arrray access:");
		safeArrayAccess(balances, 1);
		safeArrayAccess(balances, 10);
		
		System.out.println("\n\n ---- part3: null pointer exception ---");
		
		String name=null;
		
		System.out.println("unsafe null access:");
		
		try {
			int length = name.length();
			System.out.println(length);
		}catch(NullPointerException e) {
			System.out.println("x error: cannot use null object!");
			System.out.println("the string is null,");
		}
		
		System.out.println("\n safe null check:");
		safeStringLength(name);
		safeStringLength("anoosh");
		
		System.out.println("\n\n--- part4: number format exception ---");
		
		System.out.println("converting strings to numbers");
		
		safeParseInt("123");
		
		safeParseInt("abc");
		
		safeParseInt("12.5");
		safeParseInt("");
		
		System.out.println("\n\n--- part 5: input mismatch exception---");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("testing input validation:");
		int age = getValidInteger(scanner, "enteryourage:");
		System.out.println("you entered : " +age);
		
		
		System.out.println("\n\n--- part 7 : finally block ---");
		
		finallyDemo(true);
		finallyDemo(false);
		
		System.out.println("\n\n --- part 8 : nested try catch ---");
		
		nestedTryCatch();
		
		scanner.close();
		System.out.println("\n ===== exception demo complete====");
		
		
		
	}
	
	static void safeDivision(int a, int b) {
		try {
			int result = a/b;
			System.out.println(a + "/" + b + "=" + result);
		}catch(ArithmeticException e) {
			System.out.println("x error: cannot divide by zero!");
			System.out.println("please use a non-zero divisor.");
		}
		
	}
	
	static void demonstrateCrash() {
		int result = 10/0;
		System.out.println(result);
	}
	
	
	static void safeArrayAccess(int[] array, int index) {
		try {
			int value = array[index];
			System.out.println("array[" + index + "]=" +value);
		}catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("x error: index "+ index + "is out of bounds!");
			System.out.println("valid indices: 0 to " + (array.length -1));
		}
		
	}
	
	static void safeStringLength(String str) {
		try {
			int length = str.length();
			System.out.println("String " + str + "has length:" + length);
		}catch(NullPointerException e) {
			System.out.println("x error: string is null!");
			System.out.println("cannot get length of null string.");
		}
		
	}
	
	
	static void safeParseInt(String str) {
		try {
			int number = Integer.parseInt(str);
			System.out.println(" "+str + "converted to:"+number);
		}catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println(" " + str + "is not a valid integer!");
			System.out.println("please enter amount only (e.g.,123");
		}
	}
	
	static int getValidInteger(Scanner scanner,String prompt) {
		while(true) {
			try {
				System.out.println(prompt);
				int value = scanner.nextInt();
				return value;
			}catch(InputMismatchException e) {
				System.out.println("x invalid input! please enter a number.");
				scanner.next();
			}
		}
		
	}
	
	
	static void multipleCatchDemo(String numberStr, int divisor) {
		System.out.println("\n processing: " + numberStr + "/" + divisor);
		
		try {
			int number = Integer.parseInt(numberStr);
			int result = number / divisor;
			System.out.println(" Result : "+result);
		}catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println("x numberformatexception: invalid number format!");
			
		}catch (ArithmeticException e) {
			// TODO: handle exception
			System.out.println("x arithamaticexception : cannot divide by zero!");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("x unexpected error : "+e.getMessage());
		}
	}
	
	
	static void finallyDemo(boolean shouldSucced) {
		System.out.println("\n testing finally block (should succed=" +shouldSucced+ ")");
		
		try {
			System.out.println("executinf try block...");
			
			if(!shouldSucced) {
				throw new RuntimeException("intentional error");
			}
			System.out.println("try bolck completed succesfully!");
		}catch(RuntimeException e) {
			System.out.println("caught exception: "+e.getMessage());
		}finally{
			System.out.println("finally block always executes!");
			System.out.println("good for cleanup operations");
		}
		System.out.println("program continues after try catch finally");
	}
	
	static void nestedTryCatch() {
		System.out.println("\nouter try block:");
		
		try {
			System.out.println("outer: processing....");
			
			String[] data = {"10", "20","abc","40"};
			
			for(int i = 1; i<data.length;i++) {
				try {
					int number = Integer.parseInt(data[i]);
					System.out.println("Inner : converted "+data[i] + "="+number);
					
				}catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("inner : x invalid number "+ data[i] + " ");
				}
			}
			
			System.out.println("outer: all items processed!");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("outer: unexpected error!");
		}
		
		
			
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
}