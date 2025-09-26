import java.util.Scanner;

public class Student {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Roll Number");
		int rollNum = s.nextInt();
		System.out.println("Enter Name");
		String studName = s.next();
		System.out.println("Enter Marks in three subjects");
		double mark1 = s.nextDouble();
		double mark2 = s.nextDouble();
		double mark3 = s.nextDouble();
		double total = mark1 + mark2 + mark3;
		double avg = total / 3;
		System.out.println("Roll Number = "+rollNum);
		System.out.println("Student Name = "+studName);
		System.out.println("Total Marks = "+total);
		System.out.println("Average = "+avg);
	}

}
