import java.util.Scanner;

public class BigThree {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter three numbers");
		int num1 = s.nextInt();
		int num2 = s.nextInt();
		int num3 = s.nextInt();
		if (num1 > num2 && num1 > num3)
			System.out.println("Big = "+num1);
		else
			if (num2 > num3)
				System.out.println("Big = "+num2);
			else
				System.out.println("Big = "+num3);
	}

}
