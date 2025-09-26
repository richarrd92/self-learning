//to find area of a rectangle
import java.util.Scanner;
public class Rectangle {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter length value");
		int length = s.nextInt();
		System.out.println("Enter breadth value");
		int breadth = s.nextInt();
		int area = length * breadth;
		System.out.println("Area of Rectangle = "+area);

	}

}
