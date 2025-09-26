import java.util.Scanner;
public class ArraySum {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[] n = new int[5];
		int sum = 0;
		System.out.println("Enter 5 values");
		for(int i=0;i<n.length;i++)
		{
			n[i] = s.nextInt();
			sum = sum + n[i];
		}
		System.out.println("Sum = "+sum);
	}

}
