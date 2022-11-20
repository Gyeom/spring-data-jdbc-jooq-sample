package spring.data.jdbc.answer.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.data.jdbc.answer.dto.AnswerCreateRequest;
import spring.data.jdbc.answer.dto.AnswerResponse;
import spring.data.jdbc.answer.domain.Answer;
import spring.data.jdbc.answer.domain.AnswerRepository;
import spring.data.jdbc.exception.ErrorCode;
import spring.data.jdbc.exception.RestApiException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Transactional
    public AnswerResponse saveAnswer(final AnswerCreateRequest answerCreateRequest) {
        return AnswerResponse.from(answerRepository.save(answerCreateRequest.toEntity()));
    }

    public AnswerResponse getAnswer(final Long id) {
        return AnswerResponse.from(findById(id));
    }

    private Answer findById(final Long id) {
        return answerRepository.findById(id)
                .orElseThrow(() ->  new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}
