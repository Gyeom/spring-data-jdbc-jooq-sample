package spring.data.jdbc.channel;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@PersistenceConstructor))
@ToString
public class Channel {
    @Id
    private Long id;

    @NotBlank
    @Size(max =  100)
    private String name;

    @Size(max = 255)
    private String description;

    private AggregateReference<User, @NotNull String> createdBy;

    @NotNull
    @PastOrPresent
    private Instant createdAt;

    public Channel(String name, String description, AggregateReference<User, String> createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = Instant.now();
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }
}
