package spring.data.jdbc;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import spring.data.jdbc.dto.QuestionResponse;

import static spring.data.jdbc.QuestionAcceptanceStep.*;

@DisplayName("질문 관련 기능")
public class QuestionAcceptanceTest extends AcceptanceTest {

    @DisplayName("질문을 생성하면 응답상태 201을 반환한다.")
    @Test
    void createQuestion() {
        // when
        ExtractableResponse<Response> response =
                질문_생성_요청(1L, "OPEN", "title", "content", "gyeom");

        // then
        응답상태_검증(response, HttpStatus.CREATED);
    }

    @DisplayName("질문 목록을 조회하면 응답상태 200을 반환한다.")
    @Test
    void getQuestions() {
        // given
        Long channelId = 1L;
        ExtractableResponse<Response> createResponse1 =
                등록된_질문(channelId, "OPEN", "title", "content", "gyeom");
        ExtractableResponse<Response> createResponse2 =
                등록된_질문(channelId, "OPEN", "title2", "content2", "gyeom");

        // when
        ExtractableResponse<Response> response = 질문_목록_조회_요청(channelId);

        // then
        응답상태_검증(response, HttpStatus.OK);
    }

    @DisplayName("질문 정보를 수정하면 응답상태 200을 반환한다.")
    @Test
    void updateQuestion() {
        // given
        ExtractableResponse<Response> createResponse =
                등록된_질문(1L, "OPEN", "title", "content", "gyeom");

        // when
        ExtractableResponse<Response> response =
                질문_수정_요청(createResponse, "OPEN", "updatedTitle", "updatedContent");

        // then
        응답상태_검증(response, HttpStatus.OK);
    }

    @DisplayName("답변을 생성하면 응답상태 201을 반환한다.")
    @Test
    void createAnswer() {
        // given
        QuestionResponse questionResponse = 등록된_질문(1L, "OPEN", "title", "content", "gyeom").as(QuestionResponse.class);
        답변_생성_요청(questionResponse.getId(), "content1", "gyeom");
        답변_생성_요청(questionResponse.getId(), "content2", "gyeom");

        // when
        ExtractableResponse<Response> response =
                답변_생성_요청(questionResponse.getId(), "content3", "gyeom");

        // then
        응답상태_검증(response, HttpStatus.CREATED);
    }
}
