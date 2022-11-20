package spring.data.jdbc.question.dto;

import lombok.Value;
import spring.data.jdbc.question.domain.Status;

@Value
public class QuestionUpdateRequest {
    Status status;
    String title;
    String content;
}
