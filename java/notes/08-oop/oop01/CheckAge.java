import java.util.Scanner;

public class CheckAge {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Age");
		int age = s.nextInt();
		if (age < 0)
			System.out.println("Invalid Age");
		else
			if (age >= 18)
				System.out.println("Major");
			else
				System.out.println("Minor");
		

	}

}
