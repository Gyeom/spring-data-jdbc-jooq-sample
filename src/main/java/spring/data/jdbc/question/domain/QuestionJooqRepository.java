package spring.data.jdbc.question.domain;

import spring.data.jdbc.question.dto.QuestionResponse;

public interface QuestionJooqRepository {
    QuestionResponse findQuestionById(Long questionId);
}
