import java.util.Scanner;
import java.util.Random;

// Create variable sized array on the fly
/**
 * 
 */
public class array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of array: ");
        Random rand = new Random();
        
        for (int i = 0; i < 10; i++) {
            int size = rand.nextInt(1, 10);
            System.out.println(size);
        }
    }
    
}
