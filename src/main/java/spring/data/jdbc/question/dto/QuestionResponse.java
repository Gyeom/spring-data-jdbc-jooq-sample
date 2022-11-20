package spring.data.jdbc.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import spring.data.jdbc.answer.dto.AnswerResponse;
import spring.data.jdbc.question.domain.Question;
import spring.data.jdbc.question.domain.Status;

import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class QuestionResponse {
    Long id;
    Long channelId;
    Status status;
    String title;
    String content;
    String writer;
    List<AnswerResponse> answerResponses;

    public QuestionResponse(final Long id, final Long channelId, final Status status,
                            final String title, final String content, final String writer) {
        this.id = id;
        this.channelId = channelId;
        this.status = status;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static QuestionResponse from(final Question question) {
        return new QuestionResponse(question.getId(), question.getChannelId().getId(),
                question.getStatus(), question.getTitle(), question.getContent(),
                Objects.requireNonNull(question.getCreatedBy().getId()));
    }

    public void addAnswerResponse(List<AnswerResponse> answerResponses) {
        this.answerResponses = answerResponses;
    }
}
