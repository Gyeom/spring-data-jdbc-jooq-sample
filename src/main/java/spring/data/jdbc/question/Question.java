package spring.data.jdbc.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import spring.data.jdbc.channel.Channel;
import spring.data.jdbc.user.User;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    private AggregateReference<Channel, @NotBlank @Size(max = 200) Long> channelId;

    @NotNull
    private Status status;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 200)
    private String content;

    private AggregateReference<User, @NotNull String> createdBy;

    @Valid
    @MappedCollection(idColumn = "QUESTION_ID", keyColumn = "ID")
    @Builder.Default
    private List<Answer> answers = new ArrayList<>();

    @NotNull
    @PastOrPresent
    @Builder.Default
    private Instant createdAt = Instant.now();

    public void changeInfo(final String title, final String content, final Status status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public void addAnswer(final Answer answer) {
        answers.add(answer);
    }

    public Answer getLastAnswer() {
        return answers.get(answers.size() - 1);
    }

    public void setAnswer(final List<Answer> collect) {
        this.answers = collect;
    }
}
