package spring.data.jdbc.user;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import spring.data.jdbc.global.AcceptanceTest;

import static spring.data.jdbc.question.QuestionAcceptanceStep.응답상태_검증;
import static spring.data.jdbc.user.UserAcceptanceStep.유저_생성_요청;

@DisplayName("유저 관련 기능")
public class UserAcceptanceTest extends AcceptanceTest {

    @DisplayName("유저를 생성하면 응답상태 201을 반환한다.")
    @Test
    void createUser() {
        // when
        ExtractableResponse<Response> response =
                유저_생성_요청("daegyeom123", "gyeom");

        // then
        응답상태_검증(response, HttpStatus.CREATED);
    }
}
