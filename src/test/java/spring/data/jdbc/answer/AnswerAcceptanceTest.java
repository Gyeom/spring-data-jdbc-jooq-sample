package spring.data.jdbc.answer;

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

import java.util.Arrays;

import static spring.data.jdbc.answer.AnswerAcceptanceStep.답변_생성_요청;
import static spring.data.jdbc.answer.AnswerAcceptanceStep.등록된_답변;
import static spring.data.jdbc.channel.ChannelAcceptanceStep.등록된_채널;
import static spring.data.jdbc.question.QuestionAcceptanceStep.등록된_질문;
import static spring.data.jdbc.question.QuestionAcceptanceStep.응답상태_검증;
import static spring.data.jdbc.user.UserAcceptanceStep.등록된_유저;

@DisplayName("답변 관련 기능")
public class AnswerAcceptanceTest extends AcceptanceTest {

    ChannelResponse 등록된_채널;
    UserResponse 등록된_유저;

    @BeforeEach
    void setup() {
        등록된_유저 = 등록된_유저("daegyeom123", "gyeom").as(UserResponse.class);
        등록된_채널 = 등록된_채널("채널1", "description1", 등록된_유저.getId()).as(ChannelResponse.class);
    }

    @DisplayName("답변을 생성하면 응답상태 201을 반환한다.")
    @Test
    void createAnswer() {
        // given
        QuestionResponse questionResponse = 등록된_질문(등록된_채널.getId(), "OPEN", "title", "content", 등록된_유저.getId(), Arrays.asList("태그1", "태그2")).as(QuestionResponse.class);
        등록된_답변(questionResponse.getId(), "content1", 등록된_유저.getId());
        등록된_답변(questionResponse.getId(), "content2", 등록된_유저.getId());

        // when
        ExtractableResponse<Response> response =
                답변_생성_요청(questionResponse.getId(), "content3", 등록된_유저.getId());

        // then
        응답상태_검증(response, HttpStatus.CREATED);
    }
}
