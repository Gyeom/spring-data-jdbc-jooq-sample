package spring.data.jdbc.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.data.jdbc.dto.*;
import spring.data.jdbc.question.Answer;
import spring.data.jdbc.question.Question;
import spring.data.jdbc.question.QuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public QuestionResponse saveQuestion(QuestionCreateRequest questionCreateRequest) {
        Question question = questionCreateRequest.toEntity();
        question.addAnswer(Answer.builder().content("test").createdBy(AggregateReference.to("gyeom")).build());
        Question save = questionRepository.save(question);
        System.out.println(save.getAnswers().toString());
        return QuestionResponse.from(save);
    }

    public List<QuestionResponse> findAllQuestions(final Long channelId) {
        return questionRepository.findQuestionsByChannelId(channelId)
                .stream()
                .map(QuestionResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public QuestionResponse modify(final Long id, final QuestionUpdateRequest questionUpdateRequest) {

        Question question = findById(id);

        question.changeInfo(questionUpdateRequest.getTitle(), questionUpdateRequest.getContent(),
                questionUpdateRequest.getStatus());

        return QuestionResponse.from(questionRepository.save(question));
    }

    private Question findById(final Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "에 해당하는 질문 정보를 찾을 수 없습니다."));
    }

    public AnswerResponse saveAnswer(final Long questionId, final AnswerCreateRequest answerCreateRequest) {

        Question question = findById(questionId);
        question.addAnswer(answerCreateRequest.toEntity(questionId));
//        if(question.getAnswers().size() == 3) {
//            List<Answer> answers = question.getAnswers();
//            answers.remove(1);
//            question.setAnswer(answers);
//        }

        Question question1 = questionRepository.save(question);
        System.out.println("[TEST]: " + question1.getAnswers().toString());
        Optional<Question> byId = questionRepository.findById(questionId);

//
        System.out.println(byId.get().getAnswers().toString());
        return AnswerResponse.from(question1.getLastAnswer());
    }
}
