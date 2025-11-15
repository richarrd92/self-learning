import java.time.LocalDate;

public record Order(
        int id,
        int userId,
        double totalAmount,
        LocalDate date,
        Status status
) {}
