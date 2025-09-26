class Outer
{
	int x = 100;
	class Inner
	{
		void ppp()
		{
			System.out.println("x = "+x);
		}
	}
}
public class InnerDemo {
	public static void main(String[] args) {
		Outer.Inner innerObj = new Outer().new Inner();
		innerObj.ppp();
	}

}
