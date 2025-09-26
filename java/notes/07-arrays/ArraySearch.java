import java.util.Scanner;
public class ArraySearch {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[] n = new int[5];
		System.out.println("Enter 5 values");
		for(int i=0;i<n.length;i++)
			n[i] = s.nextInt();
		System.out.println("Enter value to search");
		int key = s.nextInt();
		if (search(n,key))
			System.out.println(key+" is found");
		else
			System.out.println(key+" is not found");

	}
	static boolean search(int[] n, int key)
	{
		for(int i=0;i<n.length;i++)
		{
			if (key == n[i])
				return true;
		}
		return false;
	}
}
