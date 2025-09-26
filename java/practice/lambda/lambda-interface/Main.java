import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // try-with-resources automatically closes the Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a number of choice: ");
            int userInput = scanner.nextInt();

            // Prettifier instance using anonymous class
            // example 1:
            // Prettifier newPrettifier = obj -> "******* " + obj + " **********";

            // example 2:
            Prettifier newPrettifier = new Prettifier() {
                // implement abstract function
                public String prettifyString(Object object){
                    return "----- " + object + " ------";
                }
            };

            // call the prettifyString method
            String result = newPrettifier.prettifyString(userInput);

            System.out.println(result);
        }

    }
}