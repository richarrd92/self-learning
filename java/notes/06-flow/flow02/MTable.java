import java.util.Scanner;
public class MTable {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a Number");
		int n = s.nextInt();
		mtable(n);
	}
	static void mtable(int n)//void means no return type
	{
		for(int i=1;i<=10;i++)
		{
			int p = n * i;
			System.out.println(n+" * "+i+" = "+p);
		}
	}
}
/*
Enter a Number 5 (n=5)

5 * 1 = 5
5 * 2 = 10
...
5 * 9 = 45
5 * 10 = 50

*/