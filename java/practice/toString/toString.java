import java.util.Scanner;

public class toString {
    public static void main(String[] args) {
        String name = "";
        int age = 0;

        // create a scanner class
        try (Scanner sc = new Scanner(System.in);) {
            // get user input
            System.out.println("Enter your name: ");
            name = sc.nextLine();
            System.out.println("Enter your age: ");
            age = sc.nextInt();
        }
        // create user object
        User user = new User(name, age);
        System.out.println(user);
    }

    // must be static since its nested 
    public static class User {
        private String name;
        private int age;

        // default constructor
        public User() {
        }

        // overloaded constructor
        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // setters
        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        // getters
        public String getName(){
            return this.name;
        }

        public int getAge() {
            return this.age;
        }

        @Override
        public String toString() {
            return "User{" +
           "name='" + name + '\'' +
           ", age=" + age +
           '}';
        }
    }
}