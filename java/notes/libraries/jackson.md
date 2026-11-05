# JACKSON

**Jackson** is a popular Java library for **JSON parsing and serialization**.  
It is part of the `com.fasterxml.jackson` suite and is often bundled by Spring Boot by default.

#### Common Modules:
- **jackson-databind** → Core module (read/write JSON to Java objects).
- **jackson-core** → Low-level streaming API for parsing JSON.
- **jackson-annotations** → Provides annotations like `@JsonIgnore`, `@JsonProperty`, etc.

#### Why use Jackson?
- Automatically converts Java objects (POJOs) to JSON and back.
- Works seamlessly with Spring Boot REST controllers (`@RestController` returns JSON by default).
- Supports annotations for fine-grained control over serialization.
- Can handle complex types (Lists, Maps, Nested Objects).
- High-performance compared to many other JSON libraries.

#### Example Dependency:
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.1</version>
</dependency>
```
**Example Usage:**
```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class Example {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Serialize
        User user = new User("Alice", 25);
        String json = mapper.writeValueAsString(user);

        // Deserialize
        User parsed = mapper.readValue(json, User.class);
    }
}
```

**NOTES**: 
- If you’re using Spring Boot, you usually don’t need to add Jackson explicitly
- Spring Boot comes with spring-boot-starter-web. You only add it if you want a specific version or more control.