import java.time.LocalDate;
import java.util.List;

public class DataSeeder {
    public static List<User> getUsers() {
        return List.of(
                new User(1, "Alice", "alice@gmail.com", Role.USER, LocalDate.of(2022, 5, 10), 5, List.of("music", "travel", "books")),
                new User(2, "Bob", "bob@yahoo.com", Role.ADMIN, LocalDate.of(2020, 2, 3), 20, List.of("sports", "coding", "gaming")),
                new User(3, "Charlie", "charlie@gmail.com", Role.USER, LocalDate.of(2023, 7, 19), 2, List.of("travel", "food")),
                new User(4, "Diana", "diana@hotmail.com", Role.MANAGER, LocalDate.of(2019, 11, 25), 15, List.of("books", "art", "yoga")),
                new User(5, "Evan", "evan@gmail.com", Role.USER, LocalDate.of(2024, 3, 5), 1, List.of("gaming", "coding")),
                new User(6, "Fiona", "fiona@yahoo.com", Role.USER, LocalDate.of(2021, 8, 12), 8, List.of("travel", "fitness", "music"))
        );
    }

    public static List<Order> getOrders() {
        return List.of(
                new Order(101, 1, 59.99, LocalDate.of(2024, 12, 1), Status.DELIVERED),
                new Order(102, 1, 15.49, LocalDate.of(2024, 11, 3), Status.CANCELLED),
                new Order(103, 2, 199.99, LocalDate.of(2025, 1, 10), Status.SHIPPED),
                new Order(104, 3, 75.00, LocalDate.of(2025, 1, 5), Status.DELIVERED),
                new Order(105, 5, 10.00, LocalDate.of(2025, 2, 14), Status.PENDING),
                new Order(106, 4, 150.75, LocalDate.of(2024, 9, 20), Status.DELIVERED),
                new Order(107, 2, 49.50, LocalDate.of(2025, 3, 22), Status.DELIVERED),
                new Order(108, 6, 89.99, LocalDate.of(2025, 5, 15), Status.SHIPPED),
                new Order(109, 1, 29.99, LocalDate.of(2025, 2, 10), Status.PENDING)
        );
    }
}
