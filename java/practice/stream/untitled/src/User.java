import java.time.LocalDate;
import java.util.List;

public record User(
        int id,
        String name,
        String email,
        Role role,
        LocalDate joinedAt,
        int loginCount,
        List<String> interests
) {}