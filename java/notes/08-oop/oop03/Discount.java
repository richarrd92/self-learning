import java.util.Scanner;
public class Discount {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Item number");
		int itemNum = s.nextInt();
		System.out.println("Enter Item Name");
		String itemName = s.next();
		System.out.println("Enter Rate and Quantity");
		double rate = s.nextDouble();
		int qty = s.nextInt();
		double amount = rate * qty;
		double discount=0.0;
		if (amount >= 1000 && amount < 2000)
			discount = 0.10 * amount;
		else
			if (amount >= 2000 && amount < 3000)
				discount = 0.15 * amount;
			else
				if (amount >= 3000 && amount < 5000)
					discount = 0.20 * amount;
				else
					if (amount >= 5000)
						discount = 0.25 * amount;
		double netAmount = amount - discount;
		System.out.println("Item Number = "+itemNum);
		System.out.println("Item Name = "+itemName);
		System.out.println("Amount = "+amount);
		System.out.println("Discount = "+discount);
		System.out.println("Net Amount = "+netAmount);

	}

}
