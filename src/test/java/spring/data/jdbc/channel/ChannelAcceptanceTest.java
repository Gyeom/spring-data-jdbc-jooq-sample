package spring.data.jdbc.channel;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import spring.data.jdbc.channel.dto.ChannelResponse;
import spring.data.jdbc.global.AcceptanceTest;
import spring.data.jdbc.question.dto.QuestionResponse;
import spring.data.jdbc.user.dto.UserResponse;

import static spring.data.jdbc.channel.ChannelAcceptanceStep.*;
import static spring.data.jdbc.question.QuestionAcceptanceStep.등록된_질문;
import static spring.data.jdbc.question.QuestionAcceptanceStep.응답상태_검증;
import static spring.data.jdbc.user.UserAcceptanceStep.등록된_유저;

@DisplayName("채널 관련 기능")
public class ChannelAcceptanceTest extends AcceptanceTest {

    @DisplayName("채널을 생성하면 응답상태 201을 반환한다.")
    @Test
    void createChannel() {
        // given
        UserResponse userResponse = 등록된_유저("daegyeom123", "gyeom").as(UserResponse.class);

        // when
        ExtractableResponse<Response> response =
                채널_생성_요청("채널1", "description", userResponse.getId());

        // then
        응답상태_검증(response, HttpStatus.CREATED);
    }

    @DisplayName("채널 목록을 조회하면 응답상태 200을 반환한다.")
    @Test
    void getChannels() {
        // given
        UserResponse userResponse = 등록된_유저("daegyeom123", "gyeom").as(UserResponse.class);
        등록된_채널("채널1", "description1", userResponse.getId());
        등록된_채널("채널2", "description2", userResponse.getId());

        // when
        ExtractableResponse<Response> response = 채널_목록_조회_요청();

        // then
        응답상태_검증(response, HttpStatus.OK);
    }

    @DisplayName("채널에 있는 질문목록을 조회하면 응답상태 200을 반환한다.")
    @Test
    void getChannelAndQuestions() {
        // given
        UserResponse userResponse = 등록된_유저("daegyeom123", "gyeom").as(UserResponse.class);
        ChannelResponse channelResponse = 등록된_채널("채널1", "description1", userResponse.getId()).as(ChannelResponse.class);
        등록된_질문(channelResponse.getId(), "OPEN", "title", "content", userResponse.getId()).as(QuestionResponse.class);
        등록된_질문(channelResponse.getId(), "OPEN", "title2", "content2", userResponse.getId()).as(QuestionResponse.class);

        // when
        ExtractableResponse<Response> response = 채널_조회_요청(channelResponse.getId());

        // then
        응답상태_검증(response, HttpStatus.OK);
    }

    @DisplayName("질문 정보를 수정하면 응답상태 200을 반환한다.")
    @Test
    void updateQuestion() {
        // given
        UserResponse userResponse = 등록된_유저("daegyeom123", "gyeom").as(UserResponse.class);
        ExtractableResponse<Response> channelResponse = 등록된_채널("채널1", "description1", userResponse.getId());

        // when
        ExtractableResponse<Response> response =
                채널_수정_요청(channelResponse, "채널2", "description2");

        // then
        응답상태_검증(response, HttpStatus.OK);
    }
}
