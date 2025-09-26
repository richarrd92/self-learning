import java.util.Scanner;
public class Factorial {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a Number");
		int n = s.nextInt();
		int f = fact(n);
		System.out.println("Factorial = "+f);

	}
	static int fact(int n)//n is an argument/parameter
	{
		int f = 1;
		for(int i=n;i>=1;i--)
			f = f * i;
		return f;
	}
}
/*
Enter a Number 5 (n=5, f=1)
f=f*i=1*5=5
f=f*i=5*4=20
f=f*i=20*3=60
f=f*i=60*2=120
f=f*i=120*1=120
Factorial = 120

*/
//A static method can access only other static members directly