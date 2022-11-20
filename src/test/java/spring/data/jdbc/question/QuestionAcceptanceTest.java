package spring.data.jdbc.question;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import spring.data.jdbc.channel.dto.ChannelResponse;
import spring.data.jdbc.global.AcceptanceTest;
import spring.data.jdbc.question.dto.QuestionResponse;
import spring.data.jdbc.user.dto.UserResponse;

import static spring.data.jdbc.answer.AnswerAcceptanceStep.등록된_답변;
import static spring.data.jdbc.channel.ChannelAcceptanceStep.등록된_채널;
import static spring.data.jdbc.question.QuestionAcceptanceStep.*;
import static spring.data.jdbc.user.UserAcceptanceStep.등록된_유저;

@DisplayName("질문 관련 기능")
public class QuestionAcceptanceTest extends AcceptanceTest {

    ChannelResponse 등록된_채널;
    UserResponse 등록된_유저;

    @BeforeEach
    void setup() {
        등록된_유저 = 등록된_유저("daegyeom123", "gyeom").as(UserResponse.class);
        등록된_채널 = 등록된_채널("채널1", "description1", 등록된_유저.getId()).as(ChannelResponse.class);
    }

    @DisplayName("질문을 생성하면 응답상태 201을 반환한다.")
    @Test
    void createQuestion() {
        // when
        ExtractableResponse<Response> response =
                등록된_질문(등록된_채널.getId(), "OPEN", "title", "content", 등록된_유저.getId());

        // then
        응답상태_검증(response, HttpStatus.CREATED);
    }

    @DisplayName("질문 목록을 조회하면 응답상태 200을 반환한다.")
    @Test
    void getQuestions() {
        // given
        등록된_질문(등록된_채널.getId(), "OPEN", "title", "content", 등록된_유저.getId()).as(QuestionResponse.class);
        등록된_질문(등록된_채널.getId(), "OPEN", "title2", "content2", 등록된_유저.getId()).as(QuestionResponse.class);

        // when
        ExtractableResponse<Response> response = 질문_목록_조회_요청(등록된_채널.getId());

        // then
        응답상태_검증(response, HttpStatus.OK);
    }

    @DisplayName("질문을 조회하면 응답상태 200을 반환한다.")
    @Test
    void getQuestion() {
        // given
        QuestionResponse questionResponse1 =
                등록된_질문(등록된_채널.getId(), "OPEN", "title", "content", 등록된_유저.getId()).as(QuestionResponse.class);
        등록된_답변(questionResponse1.getId(), "content1", "gyeom");
        등록된_답변(questionResponse1.getId(), "content2", "gyeom");
        등록된_답변(questionResponse1.getId(), "content3", "gyeom");

        // when
        ExtractableResponse<Response> response = 질문_목록_조회_요청(questionResponse1.getId());

        // then
        응답상태_검증(response, HttpStatus.OK);
    }

    @DisplayName("질문 정보를 수정하면 응답상태 200을 반환한다.")
    @Test
    void updateQuestion() {
        // given
        ExtractableResponse<Response> createResponse =
                등록된_질문(등록된_채널.getId(), "OPEN", "title", "content", 등록된_유저.getId());

        // when
        ExtractableResponse<Response> response =
                질문_수정_요청(createResponse, "OPEN", "updatedTitle", "updatedContent");

        // then
        응답상태_검증(response, HttpStatus.OK);
    }
}
