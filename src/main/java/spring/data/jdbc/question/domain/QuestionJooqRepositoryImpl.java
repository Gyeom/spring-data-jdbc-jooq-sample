package spring.data.jdbc.question.domain;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import spring.data.jdbc.answer.dto.AnswerResponse;
import spring.data.jdbc.question.dto.QuestionResponse;

import java.util.List;
import java.util.Objects;

import static spring.data.jdbc.tables.Answer.ANSWER;
import static spring.data.jdbc.tables.Question.QUESTION;

@RequiredArgsConstructor
public class QuestionJooqRepositoryImpl implements QuestionJooqRepository {

    private final DSLContext dsl;

    @Override
    public QuestionResponse findQuestionById(final Long questionId) {

        QuestionResponse questionResponse =
                dsl.select(
                        QUESTION.ID.as("id"),
                        QUESTION.CHANNEL_ID,
                        QUESTION.STATUS,
                        QUESTION.TITLE,
                        QUESTION.CONTENT,
                        QUESTION.CREATED_BY.as("writer")
                ).from(QUESTION).where(QUESTION.ID.eq(questionId)).fetchOneInto(QuestionResponse.class);

        List<AnswerResponse> answerResponses =
                dsl.select(ANSWER.ID, ANSWER.CONTENT, ANSWER.CREATED_BY.as("writer"))
                        .from(ANSWER)
                        .where(ANSWER.QUESTION_ID.eq(questionId))
                        .fetch().into(AnswerResponse.class);

        if (Objects.nonNull(questionResponse)) {
            questionResponse.addAnswerResponse(answerResponses);
        }

        return questionResponse;
    }
}
