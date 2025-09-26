class Book
{
	int bookId;
	String bookName;
	Book(int bookId, String bookName)//parameterized constructor
	{
		this.bookId = bookId;
		this.bookName = bookName;
	}
	Book(){}//default constructor
	void display()
	{
		System.out.println("Book Id : "+bookId);
		System.out.println("Book Name : "+bookName);
	}
}
public class ConstructorDemo2 {
	public static void main(String[] args) {
		Book b1 = new Book(101,"Learn Spring");
		b1.display();
		Book b2 = new Book();
		b2.display();
	}

}
