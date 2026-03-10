import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem{
	
	private static HashMap<Integer, Student> students = new HashMap<Integer, Student>();
	private static Scanner scanner= new Scanner(System.in);
	private static final String DATA_FILE = "students_data.txt";
	private static int nextStudentId = 1001;
	
	public static void main(String[] args) {
		loadFromFile();
		
	      System.out.println("╔════════════════════════════════════════════╗");
	      System.out.println("║   STUDENT GRADE MANAGEMENT SYSTEM          ║");
	      System.out.println("╚════════════════════════════════════════════╝");
	      
	      boolean running = true;
	      
	      while(running) {
	    	  	displayMenu();
	    	  	int choice = getIntInput("\nenter your choice: ");
	    	  	
	    	  	switch(choice) {
	    	  	case 1:
	    	  		addStudent();
	    	  		break;
	    	  	case 2:
	    	  		addGrade();
	    	  		break;
	    	  	case 3:
	    	  		viewAllStudents();
	    	  		break;
	    	  	case 4:
	    	  		viewStudentDetails();
	    	  		break;
	    	  	case 5:
	    	  		updateStudent();
	    	  		break;
	    	  	case 6:
	    	  		deleteStudent();
	    	  		break;
	    	  	case 7:
	    	  		findTopStudent();
	    	  		break;
	    	  	case 8:
	    	  		displayStatistics();
	    	  		break;
	    	  	case 9:
	    	  		saveToFile();
	    	  		break;
	    	  	case 10:
	    	  		loadFromFile();
	    	  		break;
	    	  	case 11:
	    	  		saveToFile();
	    	  		System.out.println("\n thank you for using student management system!");
	    	  		System.out.println("data saved. goodbye!\n");
	    	  		running = false;
	    	  		break;
	    	  	default:
	    	  		System.out.println("invalid choice! please select 1-11.");
	    	  	}
	      }
	      
	      scanner.close();  
	}
	
	private static void displayMenu() {
        System.out.println("\n╔════════════════ MAIN MENU ════════════════╗");
        System.out.println("║  1.  Add New Student                      ║");
        System.out.println("║  2.  Add Grade to Student                 ║");
        System.out.println("║  3.  View All Students                    ║");
        System.out.println("║  4.  View Student Details                 ║");
        System.out.println("║  5.  Update Student Information           ║");
        System.out.println("║  6.  Delete Student                       ║");
        System.out.println("║  7.  Find Top Student                     ║");
        System.out.println("║  8.  Display Statistics                   ║");
        System.out.println("║  9.  Save Data to File                    ║");
        System.out.println("║  10. Load Data from File                  ║");
        System.out.println("║  11. Exit                                 ║");
        System.out.println("╚═══════════════════════════════════════════╝");
    }
	
	
	private static void addStudent() {
		System.out.println("\n----------add new student-------");
		
		scanner.nextLine();
		System.out.println("enter student name:");
		String name = scanner.nextLine();
		
		if(name.trim().isEmpty()) {
			System.out.println("name cannot be empty!");
			return;
		}
		
		System.out.println("enter student email:");
		String email = scanner.nextLine();
		
		if(!email.contains("@")) {
			System.out.println("invalid email format!");
			return;
		}
		
		Student student = new Student(nextStudentId, name, email);
		students.put(nextStudentId, student);
		
		System.out.println("\n student added successfully!");
		System.out.println("student id:"+nextStudentId);
		System.out.println("name : "+name);
		System.out.println("email : "+email);
		
		nextStudentId++;
	}
	
	
	private static void addGrade() {
		System.out.println("\n==========add grade ==========");
		
		try {
			int studentId = getIntInput("enter student id:");
			
			if(!students.containsKey(studentId)) {
				throw new StudentNotFoundException(studentId);
			}
			
			Student student = students.get(studentId);
			
			int grade = getIntInput("enter grade (0-100): ");
			student.addGrade(grade);
		}catch (StudentNotFoundException e) {
			// TODO: handle exception
			e.displayErrorDetails();
		}
	}
	
	private static void viewAllStudents() {
		System.out.println("\n=============All students =========");
		
		if(students.isEmpty()) {
			System.out.println("no students in the system!");
			return;
		}
		
		System.out.println("\n total students: " +students.size());
		System.out.println("========================================================================");
		System.out.printf("%-8s %-20s %-25s %-10s %-8s%n", "ID", "Name", "Email", "Avg", "Grade");
		System.out.println("========================================================================");
		
		for(Student student : students.values()) {
			  System.out.printf("%-8d %-20s %-25s %-10.2f %-8s%n",
		                student.getstudentId(),
		                student.getname(),
		                student.getemail(),
		                student.calculateAverage(),
		                student.getLetterGrade());
		        }
		        
		        System.out.println("═══════════════════════════════════════════════════════════════");
		}
	
	private static void viewStudentDetails() {
		System.out.println("\n============== student details============");
		
		try {
			int studentId = getIntInput("enter student id: ");
			
			if(!students.containsKey(studentId)) {
				throw new StudentNotFoundException(studentId);
			}
			
			Student student = students.get(studentId);
			student.displayInfo();		
		}catch (StudentNotFoundException e) {
			// TODO: handle exception
			e.displayErrorDetails();
		}
	}
	
	public static void updateStudent() {
		System.out.println("\n============= update student ==============");
		
		try {
			int studentId = getIntInput("enter student id: ");
			
			if(!students.containsKey(studentId)) {
				throw new StudentNotFoundException(studentId);
			}
			
			Student student = students.get(studentId);
			
			scanner.nextLine();
			
			System.out.println("enter new name (or press enter to keep current:): ");
			String name = scanner.nextLine();
			if(!name.trim().isEmpty()) {
				student.setname(name);
			}
			
			System.out.println("enter new email (or press enter to keep current):");
			String email=scanner.nextLine();
			if(!email.trim().isEmpty()) {
				student.setemail(email);
			}
			
			System.out.println("student updated sucessfully!");
			student.displayInfo();
		}catch (StudentNotFoundException e) {
			// TODO: handle exception
			e.displayErrorDetails();
		}
	}
	
	private  static void deleteStudent() {
		System.out.println("\n ===========delete student===========");
		
		try {
			int studentId = getIntInput("enter student id:");
			
			if(!students.containsKey(studentId)) {
				throw new StudentNotFoundException(studentId);
			}
			
			Student student= students.get(studentId);
			
			System.out.println("\nstudent to delete:");
			System.out.println("id: "+student.getstudentId());
			System.out.println("name: "+student.getname());
			
			scanner.nextLine();
			System.out.println("\n are u sure? (yes/no):");
			String confirm = scanner.nextLine();
			
			if(confirm.equalsIgnoreCase("yes")) {
				students.remove(studentId);
				System.out.println("student deleted successfully!");
			}else {
				System.out.println("Delete cancelled.");
			}
			
			
		}catch (StudentNotFoundException e) {
			// TODO: handle exception
			e.displayErrorDetails();
		}
	}
	
	private static void findTopStudent() {
		System.out.println("\n=========top student===========");
		
		if(students.isEmpty()) {
			System.out.println("no students in the system!");
			return;
		}
		
		Student topstudent = null;
		double highestAverage = 0;
		
		for(Student student: students.values()) {
			double avg = student.calculateAverage();
			if(avg > highestAverage) {
				highestAverage = avg;
				topstudent = student;
			}
		}
		
		if(topstudent != null) {
			System.out.println("\n top student");
			topstudent.displayInfo();
		}	
	}
	
	private static void displayStatistics() {
		System.out.println("\n==========statistics============");
		
		if (students.isEmpty()){
		System.out.println("no students in the system!");
		return;
		}
		
		int totalStudent = students.size();
		int totalGrades = 0;
		double totalAverage = 0;
		int aCount=0,bCount=0,cCount=0,dCount=0,fCount=0;
		
		for(Student student : students.values()) {
			totalGrades+=student.getgrades().size();
			totalAverage+=student.calculateAverage();
			
			String letterGrade = student.getLetterGrade();
			switch(letterGrade) {
				case "A":aCount++;break;
				case "B":bCount++;break;
				case "C":cCount++;break;
				case "D":dCount++;break;
				case "F":fCount++;break;
			}
			
			
		}
		
		double classAverage = totalAverage/totalStudent;
		
		 System.out.println("\n--- Overall Statistics ---");
	        System.out.println("Total Students: " + totalStudent);
	        System.out.println("Total Grades Recorded: " + totalGrades);
	        System.out.printf("Class Average: %.2f%n", classAverage);
	        
	        System.out.println("\n--- Grade Distribution ---");
	        System.out.println("A grades: " + aCount);
	        System.out.println("B grades: " + bCount);
	        System.out.println("C grades: " + cCount);
	        System.out.println("D grades: " + dCount);
	        System.out.println("F grades: " + fCount);
		
	}
	
	private static void saveToFile() {
		System.out.println("\n=======saving data=========");
		
		try(FileWriter writer =new FileWriter(DATA_FILE)){
			
			for(Student students:students.values()) {
				writer.write(students.toFileString());
			}
			
			System.out.println("data saved successfully to "+ DATA_FILE);
			System.out.println("total students saved : "+students.size());
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("error saving data: "+e.getMessage());
		}
	}
	
	private static void loadFromFile() {
		System.out.println("\n========loading data=========");
		
		File file=new File(DATA_FILE);
		
		if(!file.exists()) {
			System.out.println("no saved data found. starting fresh!");
			return;
		}
		
		try(Scanner fileScanner = new Scanner(file)){
			students.clear();
			int count=0;
			int maxId=1000;
			
			while(fileScanner.hasNextLine()) {
				String line= fileScanner.nextLine();
				if(!line.trim().isEmpty()) {
					Student student= Student.fromFileString(line);
					students.put(student.getstudentId(),student);
					count++;
					if (student != null) {  // ✅ Added null check
	                    students.put(student.getstudentId(), student);
	                    count++;
					if(student.getstudentId() > maxId) {
						maxId = student.getstudentId();
					}
					}
				}
			}
			  
	        nextStudentId = maxId + 1;
	        
	        System.out.println("✓ Data loaded successfully!");
	        System.out.println("Total students loaded: " + count);
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("error loading data: "+e.getMessage());
		}
	}
	
	private static int getIntInput(String prompt) {
		while(true) {
			try {
				System.out.println(prompt);
				int value=scanner.nextInt();
				scanner.nextLine();
				return value;
				
			}catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("invalid input! please enter a number.");
				scanner.nextLine();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}