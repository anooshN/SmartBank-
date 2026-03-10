import java.util.ArrayList;

public class Student{
	private int studentId;
	private String name;
	private String email;
	private ArrayList<Integer> grades;
	
	public Student(int studentId, String name, String email) {
		this.studentId = studentId;
		this.name=name;
		this.email=email;
		this.grades= new ArrayList<Integer>();
	}
	
	public int getstudentId() {
		return studentId;
	}
	
	public String getemail() {
		return email;
		
	}
	
	public String getname() {
		return name;
		
	}
	
	public ArrayList<Integer> getgrades(){
		return grades;
	}
	
	
	public void setname(String name) {
		this.name = name;
	}
	
	public void setemail(String email) {
		this.email = email;
	}
	
	public void addGrade(int grade) {
		if(grade >= 0 && grade <= 100) {
			grades.add(grade);
			System.out.println("grade added successfully!");
		}else {
			System.out.println("invalid grade! must be 0-100.");
		}
	}
	
	
	public double calculateAverage() {
		if(grades.isEmpty()) {
			return 0.0;	
		}
		int sum = 0;
		for(int grade : grades) {
			sum +=grade;
		}
		
		return (double) sum/grades.size();
	}
	
	public String getLetterGrade() {
		double avg = calculateAverage();
		
		if(avg >= 90) return "A" ;
		else if (avg>=80) return "B";
		else if (avg>= 70) return "c";
		else if (avg >= 60) return "D";
		else return "F";	
		
	}
	
	public void displayInfo() {
		System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          STUDENT INFORMATION           ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Number of Grades: " + grades.size());
        
        if(!grades.isEmpty()) {
        		System.out.println("Grades:");
        		for(int i=0;i<grades.size();i++) {
        			System.out.println(grades.get(i));
        			if(i < grades.size()-1) {
        				System.out.println("  ");
        			}
        		}
        		
        		System.out.println();
        		System.out.printf("average : %.2f%n", calculateAverage());
        		System.out.println("Letter Grade: " + getLetterGrade());
        }else {
        		System.out.println("No grades yet.");
        }
	}
        
        
        public String toFileString() {
        		StringBuilder sb= new StringBuilder();
        		sb.append(studentId).append(" , ");
        		sb.append(name).append(" , ");
        		sb.append(email).append(" , ");
        		
        		for(int i=0; i< grades.size();i++) {
        			sb.append(grades.get(i));
        			if(i < grades.size()-1) {
        				sb.append(" : ");
        			}
        		}
        		
        		return sb.toString(); 
        
	}
        
        public static Student fromFileString(String line) {
        		String[] part=line.split(" , ");
        		
        		int id =  Integer.parseInt(part[0]);
        		String name = part[1];
        		String email = part[2];
        		
        		Student student = new Student(id,name,email);
        		
        		if(part.length > 3 && !part[3].isEmpty()) {
        			String[] gradStrings = part[3].split(" : ");
        			for(String gradStr : gradStrings) {
        				int grade=Integer.parseInt(gradStr.trim());
        				student.grades.add(grade);
        			}
        		}
        		return student;
        }
        
        
        
	
}