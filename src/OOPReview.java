public class OOPReview{
	public static void main(String[] args) {
		System.out.println("=======OOP Review library system ======\n");
		
		System.out.println("---- test 1: creating books-----");
		
		Book book=new Book();
		
		Book book1 = new Book("java programming","james gosling","isbn001", 45.99 , true);
		
		Book book2 = new Book("clean code","robert martin","isbn002", 39.99 , true);
		
		Book book3 = new Book("design patterns","gang of four","isbn003", 45.99 , true);
		
		
		book.displayInfo();
		
		System.out.println("\n---- test 2: book operations-----");
		 
		boolean flag = book1.borrowBook();
		if(flag) {
			System.out.println("sucess");
		}else {
			System.out.println("failure");
		}
		
		boolean flag1 = book1.borrowBook();
		if(flag1) {
			System.out.println("sucess");
		}else {
			System.out.println("falure");
		}
		
		book1.returnBook();
		
		book2.applyDiscount(20);
		
		System.out.println("\n ----- test 3: creating members -----");
		
		Member member1=new Member(1001, "alice", "alice@email", "regutlar");
		Member member2=new Member(1002, "bob", "bob@email", "premium");
		
		member1.displayInfo();
		member2.displayInfo();
		
		System.out.println("\n--- test 4: member operations----");
		
		System.out.println(member1.canBorrowMore());
		
		member1.borrowBook();
		member1.borrowBook();
		member1.borrowBook();
		
		member1.borrowBook();
		
		member1.upgradeToPremium();
		
		member1.borrowBook();
		
		member1.returnBook();
		member1.returnBook();
		
		member1.booksBorrowed();
		
		System.out.println("\n--- test 5: complete borrowing flow-----");
		
		if(book.borrowBook() && member2.canBorrowMore()) {
			member2.borrowBook();
		}
		
		book.displayInfo();
		member2.displayInfo();
		
		System.out.println("\n======== oop review complete =========");
		
	}
}