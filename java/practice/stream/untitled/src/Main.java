import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<User> users = DataSeeder.getUsers();
        List<Order> orders = DataSeeder.getOrders();

        // print all users who joined after 2021
//        users.stream()
//                .filter(u -> u.joinedAt().getYear() > 2021)
//                .map(User::name)
//                .forEach(System.out::println);

        // Get a list of all unique email domains (e.g., "gmail.com", "yahoo.com").
//        users.stream()
//                .map(User::email)
//                .map(email -> email.substring(email.indexOf("@") + 1))
//                .distinct()
//                .forEach(System.out::println);

        // Find the total number of users with the role USER.
//        long total = users.stream()
//                .filter(user -> user.role() == Role.USER)
//                .count();
//        System.out.println("the total number of user with USER role = " + total);
//
//        long total2 = users.stream()
//                .map(User::role)
//                .filter(role -> role == Role.ADMIN)
//                .count();
//        System.out.println("the total number of user with ADMIN role = " + total2);
//
//        long total3 = users.stream()
//                .map(User::role)
//                .filter(role -> role == Role.MANAGER)
//                .count();
//        System.out.println("the total number of user with MANAGER role = " + total3);

        // Sort users by joinedAt date (most recent first).
//        users.stream()
//                .sorted((user1, user2) -> user1.joinedAt().compareTo(user2.joinedAt()))
//                .forEach(user -> System.out.println(user.name() + ": " + user.joinedAt()));

        // Sort users by joinedAt date using method reference in reverse order.
//        users.stream()
//                .sorted(Comparator.comparing(User::joinedAt).reversed())
//                .forEach(user -> System.out.println(user.name() + ": " + user.joinedAt()));

        // Return the names of users who have "travel" as an interest.
//        users.stream()
//                .filter(user -> user.interests().contains("travel"))
//                .forEach(user -> System.out.println(user.name()));

        // Count how many users registered per email domain.
//        Map<String, Long> usersPerDomain = users.stream()
//                .map(User::email)
//                .map(email -> email.substring(email.indexOf("@") + 1))
//                .collect(Collectors.groupingBy(
//                        domain -> domain,
//                        Collectors.counting()
//                ));
//        System.out.println(usersPerDomain);
//
        // Find the average loginCount for users by Role.
//        Map<Role, Double> avgLoginPerRole = users.stream()
//                .collect(Collectors.groupingBy(
//                        User::role,
//                        Collectors.averagingInt(User::loginCount)
//                ));
//        System.out.println(avgLoginPerRole);

        // Find the top 2 most common interests among all users.
//        Map<String, Long> usersPerInterest = users.stream()
//                .flatMap(user -> user.interests().stream())
//                .collect(Collectors.groupingBy(
//                        interest -> interest,
//                        Collectors.counting()
//                ));
//        System.out.println(usersPerInterest);
//
//        usersPerInterest.entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                .limit(2)
//                .forEach(entry ->
//                        System.out.println(entry.getKey() + " → " + entry.getValue())
//                );

        // Return lists of grouped interests
//        Map<String, List<String>> listOfGroupedInterests = users.stream()
//                .flatMap(
//                        user -> user.interests().stream()
//                        .map(interest -> Map.entry(interest, user.name())))
//                .collect(
//                        Collectors.groupingBy(Map.Entry::getKey,
//                        Collectors.mapping(
//                                Map.Entry::getValue,
//                                Collectors.toList())));
//        System.out.println(listOfGroupedInterests);

        // Map each User to their List<Order> (like Map<User, List<Order>>).
//        Map<Integer, List<Integer>> ordersPerUser = orders.stream()
//                .collect(Collectors.groupingBy(
//                        Order::userId,                              // group by userId
//                        Collectors.mapping(Order::id, Collectors.toList()) // collect order ids into a list
//                ));
//        System.out.println(ordersPerUser);

        // For each user, calculate their total amount spent across all orders.
        Map<Integer, Double> userTotalSpent1 = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::userId,
                        Collectors.summingDouble(Order::totalAmount)
                ));
        System.out.println(userTotalSpent1);

        // Find the user name(s) who spent the most in total.
        // 1: map: user -> total amount spent
        Map<Integer, Double> userTotalSpent = orders.stream()
                .collect(Collectors.groupingBy(Order::userId,
                        Collectors.summingDouble(Order::totalAmount)));

        // 2: get the highest amount spent
        double maxSpent = userTotalSpent.values().stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);

        // 3: find user total amount spent matches the highest spent
        // 3.1: return their name
        users.stream()
                .filter(user -> userTotalSpent.getOrDefault(user.id(), 0.0) == maxSpent)
                .forEach(user -> System.out.println(user.name() + " → $" + maxSpent));

    }
}