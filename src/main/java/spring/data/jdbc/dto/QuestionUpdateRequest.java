package spring.data.jdbc.dto;

import lombok.Value;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.question.Question;
import spring.data.jdbc.question.Status;

@Value
public class QuestionUpdateRequest {
    Status status;
    String title;
    String content;

    public Question toEntity(Long id) {
        return Question.builder()
                        .id(id)
                        .title(title)
                        .content(content)
                        .status(Status.OPEN)
                        .build();
    }
}
