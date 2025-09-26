class MyClass
{
	int m;//instance or non-static variable
	static int n;//class or static variable
	void ppp() //instance or non-static method
	{
		m = 10;
		n = 20;
	}
	static void qqq() //class or static method
	{
		//m = 30;//error - non-static variable m cannot be accessed from static context
		n = 40;
	}
	void display()
	{
		System.out.println("m="+m+" "+"n="+n);
	}
}
public class StaticDemo {
	public static void main(String[] args) {
		MyClass mc1 = new MyClass();
		mc1.ppp();
		mc1.display();//m=10 n=20
		MyClass mc2 = new MyClass();
		mc2.display();//m=0 n=20
		mc2.qqq();
		mc1.display();//m=10 n=40
		mc2.display();//m=0 n=40
		MyClass.n = 50;
		mc1.display();//m=10 n=50
		mc2.display();//m=0 n=50
	}

}
