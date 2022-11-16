package spring.data.jdbc.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.data.jdbc.application.QuestionService;
import spring.data.jdbc.dto.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/questions")
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionCreateRequest questionRequest) {
        QuestionResponse question = questionService.saveQuestion(questionRequest);
        return ResponseEntity.created(URI.create("/questions/" + question.getId())).body(question);
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionResponse>> getQuestions(@RequestParam Long channelId) {
        return ResponseEntity.ok().body(questionService.findAllQuestions(channelId));
    }

    @PutMapping("/questions/{questionId}")
    public ResponseEntity<QuestionResponse> modifyQuestion(@PathVariable Long questionId, @RequestBody QuestionUpdateRequest questionUpdateRequest) {
        return ResponseEntity.ok().body(questionService.modify(questionId, questionUpdateRequest));
    }

    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<AnswerResponse> createQuestion(@PathVariable Long questionId, @RequestBody AnswerCreateRequest answerCreateRequest) {
        AnswerResponse answer = questionService.saveAnswer(questionId, answerCreateRequest);
        return ResponseEntity.created(URI.create("/questions/answers/")).body(answer);
    }
}
