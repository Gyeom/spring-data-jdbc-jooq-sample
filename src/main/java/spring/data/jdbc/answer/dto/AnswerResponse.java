package spring.data.jdbc.answer.dto;

import lombok.Value;
import spring.data.jdbc.answer.domain.Answer;

@Value
public class AnswerResponse {
    Long id;
    String content;
    String writer;

    public static AnswerResponse from(final Answer answer) {
        return new AnswerResponse(answer.getId(), answer.getContent(), answer.getCreatedBy().getId());
    }
}
