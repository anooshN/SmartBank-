import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIODemo{
	public static void main(String[] args) {
		System.out.println("===== file I/O demo =====\n");
		
		System.out.println("part 1 : writeing to file ---");
		
		try (FileWriter writer=new FileWriter("students.txt")) {
			writer.write("alice,85\n");
			writer.write("bob,92\n");
			writer.write("charlie,78\n");
			System.out.println("data written to student.txt");
		}catch (IOException e) {
			// TODO: handle exception
			
			System.out.println("error writing file:"+e.getMessage());
		}
		
		
		System.out.println("part 2: reading from file -------");
		
		try {
			File file=new File("students.txt");
			Scanner scanner=new Scanner(file);
			
			System.out.println("student data:");
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] part= line.split(",");
				String name= part[0];
				int grade = Integer.parseInt(part[1]);
				System.out.println(name + "scored" + grade);
				
			}
			scanner.close();
			
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("error reading file: "+ e.getMessage());
		}
		
		System.out.println("\n---part 3: appending to file-------");
		
		try(FileWriter file= new FileWriter("students.txt",true)){
			file.write("david,88\n");
			System.out.println("new student added:");
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("error appending: "+e.getMessage());
		}
		
		
		System.out.println("\n -------part 4: file operations--");
		
		File file=new File("students.txt");
		System.out.println("file exits? "+ file.exists());
		System.out.println("file size : "+file.length()+ "bytes");
		System.out.println("file path : " +file.getAbsolutePath());
		
		System.out.println("\n=====file I/O demo complete======");
	}
}