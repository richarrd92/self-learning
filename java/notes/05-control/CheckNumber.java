//shortcut to import the packages => ctrl + shift + o
import java.util.Scanner;

public class CheckNumber {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a Number");
		int num = s.nextInt();
		if (num > 0)
			System.out.println(num+" is Positive");
		else
			if (num < 0)
				System.out.println(num+" is Negative");
			else
				System.out.println(num+" is Zero");
	}

}
