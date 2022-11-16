package spring.data.jdbc.dto;

import lombok.Value;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.question.Answer;

@Value
public class AnswerCreateRequest {
    String content;
    String writer;

    public Answer toEntity(final Long questionId) {
        return Answer.builder()
//                .questionId(AggregateReference.to(questionId))
                .version(0L)
                .content(content)
                .createdBy(AggregateReference.to(writer))
                .build();
    }
}
