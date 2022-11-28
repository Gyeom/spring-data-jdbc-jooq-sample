package spring.data.jdbc.question.dto;

import lombok.Value;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.question.domain.Question;
import spring.data.jdbc.question.domain.Status;
import spring.data.jdbc.question.domain.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class QuestionCreateRequest {
    Long channelId;
    Status status;
    String title;
    String content;
    String writer;
    List<String> tags;

    public Question toEntity() {
        return Question.builder()
                .version(0L)
                .channelId(AggregateReference.to(channelId))
                .title(title)
                .content(content)
                .status(Status.OPEN)
                .createdBy(AggregateReference.to(writer))
                .tags(create(tags))
                .build();
    }

    private List<Tag> create(final List<String> tags) {
        return tags.stream()
                .map(name -> Tag.builder().name(name).build())
                .collect(Collectors.toList());
    }
}
