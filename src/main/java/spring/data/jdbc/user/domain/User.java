package spring.data.jdbc.user.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.*;
import java.time.Instant;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@Table("USERS")
public class User {
    @Id
    @Size(max = 100)
    private String id;

    @PositiveOrZero
    @Version
    private long version;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @PastOrPresent
    @Builder.Default
    private Instant createdAt = Instant.now();
}
