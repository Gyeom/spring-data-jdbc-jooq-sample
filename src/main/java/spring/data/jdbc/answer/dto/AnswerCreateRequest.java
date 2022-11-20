package spring.data.jdbc.answer.dto;

import lombok.Value;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.answer.domain.Answer;

@Value
public class AnswerCreateRequest {
    Long questionId;
    String content;
    String writer;

    public Answer toEntity() {
        return Answer.builder()
                .questionId(AggregateReference.to(questionId))
                .version(0L)
                .content(content)
                .createdBy(AggregateReference.to(writer))
                .build();
    }
}
