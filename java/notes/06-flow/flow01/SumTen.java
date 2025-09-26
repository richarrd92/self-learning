import java.util.Scanner;
public class SumTen {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n, i=1, sum=0;
		while (i <= 10)
		{
			System.out.println("Enter a Number");
			n = s.nextInt();
			sum = sum + n;//sum += n;
			i++;//i=11
		}
		System.out.println("Sum = "+sum);
	}

}
