package spring.data.jdbc.channel.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.user.domain.User;

import javax.validation.constraints.*;
import java.time.Instant;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@PersistenceConstructor))
public class Channel {
    @Id
    private Long id;

    @PositiveOrZero
    @Version
    long version;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String description;

    private AggregateReference<User, @NotNull String> createdBy;

    @NotNull
    @PastOrPresent
    @Builder.Default
    private Instant createdAt = Instant.now();

    public Channel(String name, String description, AggregateReference<User, String> createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }
}
