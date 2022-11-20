package spring.data.jdbc.answer.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.data.jdbc.answer.application.AnswerService;
import spring.data.jdbc.answer.dto.AnswerCreateRequest;
import spring.data.jdbc.answer.dto.AnswerResponse;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/answers")
    public ResponseEntity<AnswerResponse> createAnswers(@RequestBody AnswerCreateRequest answerCreateRequest) {
        AnswerResponse answer = answerService.saveAnswer(answerCreateRequest);
        return ResponseEntity.created(URI.create("/answers/" + answer.getId())).body(answer);
    }

    @GetMapping("/answers/{answerId}")
    public ResponseEntity<AnswerResponse> getAnswer(@PathVariable Long answerId) {
        return ResponseEntity.ok().body(answerService.getAnswer(answerId));
    }
}
