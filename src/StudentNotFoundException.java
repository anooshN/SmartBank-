public class StudentNotFoundException extends Exception{
	
	public int studentId;
	
	public StudentNotFoundException(int studentId) {
		super("student not found : ID " + studentId);
		this.studentId = studentId;
	}
	
	public int getstudentId() {
		return studentId;
	}
	
	public void displayErrorDetails() {
		System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       STUDENT NOT FOUND ERROR          ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Student ID: " + studentId);
        System.out.println("\nThis student does not exist in the system.");
        System.out.println("Please verify the ID and try again.");
	}
}