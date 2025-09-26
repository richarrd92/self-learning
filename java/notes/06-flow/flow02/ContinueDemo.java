public class ContinueDemo {
	public static void main(String[] args) {
		int n=1;
		while (n <= 10)
		{
			if (n == 5)
			{
				n++;
				continue;
			}
			System.out.print(n+" ");
			n++;
		}
	}
}
/*
1 2 3 4 6 7 8 9 10
*/