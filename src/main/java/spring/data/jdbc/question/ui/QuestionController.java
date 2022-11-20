package spring.data.jdbc.question.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.data.jdbc.question.application.QuestionService;
import spring.data.jdbc.question.dto.QuestionCreateRequest;
import spring.data.jdbc.question.dto.QuestionResponse;
import spring.data.jdbc.question.dto.QuestionUpdateRequest;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/questions")
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionCreateRequest questionCreateRequest) {
        QuestionResponse question = questionService.saveQuestion(questionCreateRequest);
        return ResponseEntity.created(URI.create("/questions/" + question.getId())).body(question);
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionResponse>> getQuestions(@RequestParam Long channelId) {
        return ResponseEntity.ok().body(questionService.findAllQuestions(channelId));
    }

    @GetMapping("/questions/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok().body(questionService.getQuestion(questionId));
    }

    @PutMapping("/questions/{questionId}")
    public ResponseEntity<QuestionResponse> modifyQuestion(@PathVariable Long questionId,
                                                           @RequestBody QuestionUpdateRequest questionUpdateRequest) {
        return ResponseEntity.ok().body(questionService.modifyQuestion(questionId, questionUpdateRequest));
    }

    @PutMapping("/questions/{questionId}/status")
    public ResponseEntity<Void> modifyStatus(@PathVariable Long questionId,
                                                           @RequestBody QuestionUpdateRequest questionUpdateRequest) {
        questionService.modifyStatus(questionId, questionUpdateRequest);
        return ResponseEntity.ok().build();
    }
}
