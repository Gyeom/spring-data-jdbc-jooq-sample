package spring.data.jdbc.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.data.jdbc.answer.dto.AnswerResponse;
import spring.data.jdbc.question.domain.Question;
import spring.data.jdbc.question.domain.Status;
import spring.data.jdbc.question.domain.Tag;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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
    List<String> tags;
    List<AnswerResponse> answerResponses;

    public QuestionResponse(final Long id, final Long channelId, final Status status,
                            final String title, final String content, final String writer, final List<String> tags) {
        this.id = id;
        this.channelId = channelId;
        this.status = status;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.tags = tags;
    }

    public static QuestionResponse from(final Question question) {
        return new QuestionResponse(question.getId(), question.getChannelId().getId(),
                question.getStatus(), question.getTitle(), question.getContent(),
                Objects.requireNonNull(question.getCreatedBy().getId()), question.getTags().stream().map(Tag::getName).collect(Collectors.toList()));
    }

    public void addAnswerResponse(List<AnswerResponse> answerResponses) {
        this.answerResponses = answerResponses;
    }

    public void addTagResponse(List<String> tags) {
        this.tags = tags;
    }
}
