public class Member{
	private int memberId;
	private String name;
	private String email;
	private String membershipType;
	private int booksBorrowed = 0;
	
	
	
	public Member(int memberId, String name, String email, String membershipType) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.membershipType = membershipType;
	}
	
	public String getname() {
		return name;
		
	}
	
	public String getemail() {
		return email;
	}
	
	public String membershipType() {
		return membershipType;
	}
	
	public int booksBorrowed() {
		return booksBorrowed;
	}
	
	public void setname( String name) {
		this.name = name;
		
	}
	
	public void setemail(String email) {
		this.email=email;
	}
	
	public void setmembershipType(String membershipType) {
		this.membershipType = membershipType;
	}
	
	public boolean canBorrowMore() {
		if(membershipType.equals("regular")) {
			return booksBorrowed < 3;
		}else if(membershipType.equals("premium")) {
			return booksBorrowed < 5;
		}
		return false;
	}
	
	public boolean borrowBook() {
		if(canBorrowMore()) {
			booksBorrowed++;
			return true;
		}
		return false;
	}
	
	public void returnBook() {
		if(booksBorrowed > 0) {
			booksBorrowed--;
		}
	}
	
	public void upgradeToPremium() {
		membershipType="premium";
	}
	
	public void displayInfo() {
		System.out.println("Member Id : "+memberId);
		System.out.println("name : "+name);
		System.out.println("Membership type:"+membershipType);
		System.out.println("books borrowed:"+ booksBorrowed);
	}
	
}