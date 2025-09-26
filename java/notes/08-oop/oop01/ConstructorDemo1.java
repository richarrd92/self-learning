class Sample
{
	int m;
	int n;
	Sample(int m, int n)//constructor
	{
		this.m = m;
		this.n = n;
	}
	void display()
	{
		System.out.println("m="+m+" "+"n="+n);
	}
}
public class ConstructorDemo1 {
	public static void main(String[] args) {
		Sample s = new Sample(10,20);
		s.display();//m=10 n=20
	}

}
