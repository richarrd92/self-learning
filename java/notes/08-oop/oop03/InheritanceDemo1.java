class Base
{
	int i;
	int j;
	void inputIJ(int i, int j)
	{
		this.i = i;
		this.j = j;
	}
	void displayIJ()
	{
		System.out.println("i="+i+" "+"j="+j);
	}
}
class Derived extends Base
{
	int k;
	void inputK(int k)
	{
		this.k = k;
	}
	void displayK()
	{
		System.out.println("k="+k);
	}
}
public class InheritanceDemo1 {
	public static void main(String[] args) {
		Base b = new Base();//b=i=0, b.j=0
		b.inputIJ(10,20);//b.i=10, b.j=20
		b.displayIJ();//i=10 j=20
		Derived d = new Derived();//d.i=0, d.j=0, d.k=0
		d.inputIJ(30,40);//d.i=30, d.j=40
		d.displayIJ();//i=30 j=40 
		d.inputK(50);//d.k=50
		d.displayK();//k=50
	}

}
