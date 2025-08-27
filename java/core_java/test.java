package core_java;

public class test {
    public static void main(String[] args) {
        int x = 5;
        int y = 5;
        int test1 = x++; // post - increment later
        int test2 = ++y; // pre - increment first
        System.out.println(test1);
        System.out.println(test2);
    }
}