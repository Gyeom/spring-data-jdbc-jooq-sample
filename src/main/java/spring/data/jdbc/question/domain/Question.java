package spring.data.jdbc.question.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.channel.domain.Channel;
import spring.data.jdbc.user.domain.User;

import javax.validation.constraints.*;
import java.time.Instant;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Question {
    @Id
    private Long id;

    @PositiveOrZero
    @Version
    private long version;

    private AggregateReference<Channel, Long> channelId;

    @NotNull
    private Status status;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 200)
    private String content;

    private AggregateReference<User, @NotNull String> createdBy;

    @NotNull
    @PastOrPresent
    @Builder.Default
    private Instant createdAt = Instant.now();

    public void changeInfo(final String title, final String content, final Status status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }
}
