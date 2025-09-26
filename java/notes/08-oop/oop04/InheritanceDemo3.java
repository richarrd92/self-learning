//Constructors in Inheritance
package mypack2;
class Base
{
	int i;
	int j;
	Base(int i, int j)//parameterized constructor
	{
		this.i = i;
		this.j = j;
	}
	Base(){}//default constructor
	void display()
	{
		System.out.println("i="+i+" "+"j="+j);
	}
}
class Derived extends Base
{
	int k;
	Derived(int k)
	{
		//super();//added implicitly - invokes Base class default constructor
		super(30,40);//call explicitly - invokes Base class parameterized constructor
		this.k = k;
	}
	Derived(int i, int j, int k)
	{
		super(i,j);
		this.k = k;
	}
	void display()//overridden method
	{
		super.display();//invokes Base class display()
		System.out.println("k="+k);
	}
}
public class InheritanceDemo3 {
	public static void main(String[] args) {
		Base b = new Base(10,20);
		b.display();//i=10 j=20
		Derived d = new Derived(50);
		d.display();//i=30 j=40 k=50
		Derived d1 = new Derived(60,70,80);
		d1.display();//i=60 j=70 k=80
	}

}

