package spring.data.jdbc.dto;

import lombok.Value;
import spring.data.jdbc.question.Answer;
import spring.data.jdbc.question.Question;
import spring.data.jdbc.question.Status;

import java.util.Objects;

@Value
public class AnswerResponse {
//    Long id;
    String content;
    String writer;

    public static AnswerResponse from(final Answer answer) {
//        answer.getId(),
        return new AnswerResponse(answer.getContent(), answer.getCreatedBy().getId());
    }
}
