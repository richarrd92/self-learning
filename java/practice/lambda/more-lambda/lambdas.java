import java.util.function.BiFunction;

public class lambdas {
    public static void main(String[] args) {
        
        // part 1
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        int res = add.apply(533, 8984);
        System.out.println("the result: " + res);

    }
}
