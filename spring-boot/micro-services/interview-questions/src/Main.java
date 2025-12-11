//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    public static void main(String[] args) {

        PayPal payPal = new PayPal();
        Data data = new Data();

        System.out.println("Example 1");
        payPal.displayData(payPal.mapCommon(payPal.mapData(data.enrollments1)));
        System.out.println("Example 2");
        payPal.displayData(payPal.mapCommon(payPal.mapData(data.enrollments2)));
        System.out.println("Example 3");
        payPal.displayData(payPal.mapCommon(payPal.mapData(data.enrollments3)));
    }



}