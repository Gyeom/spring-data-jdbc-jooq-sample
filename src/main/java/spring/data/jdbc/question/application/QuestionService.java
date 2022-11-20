package spring.data.jdbc.question.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.data.jdbc.exception.ErrorCode;
import spring.data.jdbc.exception.RestApiException;
import spring.data.jdbc.question.domain.Status;
import spring.data.jdbc.question.dto.QuestionCreateRequest;
import spring.data.jdbc.question.dto.QuestionResponse;
import spring.data.jdbc.question.dto.QuestionUpdateRequest;
import spring.data.jdbc.question.domain.Question;
import spring.data.jdbc.question.domain.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public QuestionResponse saveQuestion(QuestionCreateRequest questionCreateRequest) {
        return QuestionResponse.from(questionRepository.save(questionCreateRequest.toEntity()));
    }

    public List<QuestionResponse> findAllQuestions(final Long channelId) {
        return questionRepository.findQuestionsByChannelId(channelId)
                .stream()
                .map(QuestionResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public QuestionResponse modifyQuestion(final Long id, final QuestionUpdateRequest questionUpdateRequest) {

        Question question = findById(id);

        question.changeInfo(questionUpdateRequest.getTitle(), questionUpdateRequest.getContent(),
                questionUpdateRequest.getStatus());

        return QuestionResponse.from(questionRepository.save(question));
    }

    @Transactional
    public void modifyStatus(final Long id, final QuestionUpdateRequest questionUpdateRequest) {
        if(!questionRepository.changeStatus(id, questionUpdateRequest.getStatus())) {
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private Question findById(final Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public QuestionResponse getQuestion(final Long questionId) {
        return questionRepository.findQuestionById(questionId);
    }
}
