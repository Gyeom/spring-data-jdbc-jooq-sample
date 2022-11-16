package spring.data.jdbc.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.Instant;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class User {
    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @PastOrPresent
    @Builder.Default
    private Instant createdAt = Instant.now();
}
