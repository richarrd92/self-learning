import java.util.Scanner;
public class BreakDemo {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n,sum=0;
		while (true)//infinite loop  
		{
			System.out.println("Enter a Number to stop enter zero(0)");
			n = s.nextInt();//n=0
			if (n == 0)
				break;
			sum = sum + n;//sum = 13 + 6 = 19
		}
		System.out.println("Sum = "+sum);
	}

}
