//Polymorphism - Method overloading and Method overriding
package mypack1;
class Base
{
	int i;
	int j;
	void input(int i, int j)
	{
		this.i = i;
		this.j = j;
	}
	void display()
	{
		System.out.println("i="+i+" "+"j="+j);
	}
}
class Derived extends Base
{
	int k;
	void input(int k)//overloaded method
	{
		this.k = k;
	}
	void display()//overridden method
	{
		super.display();//invokes Base class display()
		System.out.println("k="+k);
	}
}
public class InheritanceDemo2 {
	public static void main(String[] args) {
		Base b = new Base();//b=i=0, b.j=0
		b.input(10,20);//b.i=10, b.j=20
		b.display();//i=10 j=20
		Derived d = new Derived();//d.i=0, d.j=0, d.k=0
		d.input(30,40);//d.i=30, d.j=40
		d.display();//i=30 j=40 k=0 
		d.input(50);//d.k=50
		d.display();//i=30 j=40 k=50
	}

}
