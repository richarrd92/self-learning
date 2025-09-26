interface Shape
{
	void draw();
	default void fill()
	{
		System.out.println("Filling");
	}
}
class Circle implements Shape
{
	public void draw()
	{
		System.out.println("Drawing a Circle");
	}
}
public class InterfaceDemo {
	public static void main(String[] args) {
		//Shape s = new Shape();//error
		Shape s = new Circle();
		s.draw();
		s.fill();
	}

}
/*
When implementing (overriding) interface methods, overridden method should be public
*/