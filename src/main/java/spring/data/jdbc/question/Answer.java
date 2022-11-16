package spring.data.jdbc.question;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.user.User;

import javax.validation.constraints.*;
import java.time.Instant;

@Value
@Builder
public class Answer {
    @Id
    Long id;

    @PositiveOrZero
    @Version
    long version;

    @NotBlank
    @Size(max = 200) String content;

//    AggregateReference<Question, @NotNull Long> questionId;

    AggregateReference<User, @NotNull String> createdBy;

    @NotNull
    @PastOrPresent
    @Builder.Default
    Instant createdAt = Instant.now();
}
