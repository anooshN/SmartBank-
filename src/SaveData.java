import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class SaveData{
	public static void main(String[] args) {
		try {
			FileWriter writer = new FileWriter("balance.txt");
			writer.write("5001,1000.50\n");
			writer.write("5002,2500.75\n");
			writer.close();
			System.out.println("datasaved");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error  saving data: "+e.getMessage());
		}
		
		try {
			File file = new File("balance.txt");
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) {
				String lineString = scanner.nextLine();
				System.out.println("loaded : "+lineString);
			}
			scanner.close();
			System.out.println("data loaded!");
		}catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("file not found!");
		}
	}
}