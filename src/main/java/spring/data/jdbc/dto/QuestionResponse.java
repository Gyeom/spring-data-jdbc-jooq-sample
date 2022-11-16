package spring.data.jdbc.dto;

import lombok.Value;
import spring.data.jdbc.question.Question;
import spring.data.jdbc.question.Status;

import java.util.Objects;

@Value
public class QuestionResponse {
    Long id;
    Long channelId;
    Status status;
    String title;
    String content;
    String writer;

    public static QuestionResponse from(final Question question) {
        return new QuestionResponse(question.getId(), question.getChannelId().getId(),
                question.getStatus(), question.getTitle(), question.getContent(),
                Objects.requireNonNull(question.getCreatedBy().getId()));
    }
}
