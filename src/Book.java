public class Book{
	private String title;
	private String author;
	private String isbn;
	private double price;
	boolean isAvailable;
	
	public Book() {
		// TODO Auto-generated constructor stub
		author = "unknown";
		title ="unknown";
		isbn = "unknown";
		price = 0.0;
		isAvailable=true;
	}

	public Book(String title, String author, String isbn, double price, boolean isAvailable) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public double getPrice() {
		return price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	public boolean borrowBook() {
		if(isAvailable) {
			isAvailable = false;
			return true;
		}
		return true;
	}
	
	public void returnBook() {
		isAvailable = true;
		
	}
	
	
	public void applyDiscount(double percentage) {
		price = price - (price * percentage/100);
	}
	
	
	public void displayInfo() {
		System.out.println("title :" +title);
		System.out.println("Atuhtor :"+ author);
		System.out.println("isbn : "+isbn);
		System.out.println("price : "+price);
		System.out.println("available : "+isAvailable);
	}
	
	
}