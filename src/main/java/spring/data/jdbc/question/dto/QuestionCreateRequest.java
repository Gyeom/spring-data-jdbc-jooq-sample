package spring.data.jdbc.question.dto;

import lombok.Value;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.question.domain.Question;
import spring.data.jdbc.question.domain.Status;

@Value
public class QuestionCreateRequest {
    Long channelId;
    Status status;
    String title;
    String content;
    String writer;

    public Question toEntity() {
        return Question.builder()
                        .version(0L)
                        .channelId(AggregateReference.to(channelId))
                        .title(title)
                        .content(content)
                        .status(Status.OPEN)
                        .createdBy(AggregateReference.to(writer))
                        .build();
    }
}