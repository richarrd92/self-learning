
public class Odd {
	public static void main(String[] args) {
		int n = 1;
		while (n <= 100)
		{
			if (n % 2 != 0)
				System.out.print(n+" ");
			n++;//n=101
		}
	}
}
/*
output
------
1 3 5 7 9 .. 97 99
*/