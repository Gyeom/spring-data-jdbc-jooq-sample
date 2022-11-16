package spring.data.jdbc;


import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionAcceptanceStep extends AcceptanceTest {
    public static ExtractableResponse<Response> 등록된_질문(Long channelId, String status,
                                                                String title, String content, String writer) {
        return 질문_생성_요청(channelId, status, title, content, writer);
    }

    public static ExtractableResponse<Response> 질문_생성_요청(Long channelId, String status,
                                                            String title, String content, String writer) {

        Map<String, Object> params = new HashMap<>();
        params.put("channelId", channelId);
        params.put("status", status);
        params.put("title", title);
        params.put("content", content);
        params.put("writer", writer);

        //@formatter:off
        return RestAssured.given()
                                .log().all()
                                .body(params)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                          .when()
                                .post("/questions")
                          .then()
                                .log().all()
                          .extract();
        //@formatter:on
    }

    public static ExtractableResponse<Response> 답변_생성_요청(Long questionId, String content,
                                                         String writer) {

        Map<String, Object> params = new HashMap<>();
        params.put("questionId", questionId);
        params.put("content", content);
        params.put("writer", writer);

        //@formatter:off
        return RestAssured.given()
                                .log().all()
                                .body(params)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                          .when()
                                .post("/questions/" + questionId + "/answers")
                          .then()
                                .log().all()
                          .extract();
        //@formatter:on
    }

    public static ExtractableResponse<Response> 질문_목록_조회_요청(final Long channelId) {

        //@formatter:off
        return RestAssured.given()
                                .param("channelId", channelId)
                                .log().all()
                          .when()
                                .get("/questions")
                          .then()
                                .log().all()
                          .extract();
        //@formatter:on
    }

    public static ExtractableResponse<Response> 질문_수정_요청(
            ExtractableResponse<Response> response, String status, String title, String content) {

        String uri = response.header("Location");
        Map<String, String> params = new HashMap<>();
        params.put("status", status);
        params.put("title", title);
        params.put("content", content);

        //@formatter:off
        return RestAssured.given()
                                .log().all()
                                .body(params)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                          .when()
                                .put(uri)
                          .then()
                                .log().all()
                          .extract();
        //@formatter:on
    }

    static void 응답상태_검증(final ExtractableResponse<Response> response, final HttpStatus ok) {
        assertThat(response.statusCode()).isEqualTo(ok.value());
    }
}

