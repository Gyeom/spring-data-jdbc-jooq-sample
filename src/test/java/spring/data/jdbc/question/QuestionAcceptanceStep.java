package spring.data.jdbc.question;


import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionAcceptanceStep {
    public static ExtractableResponse<Response> 등록된_질문(Long channelId, String status,
                                                                String title, String content, String writer, List<String> tags) {
        return 질문_생성_요청(channelId, status, title, content, writer, tags);
    }

    public static ExtractableResponse<Response> 질문_생성_요청(Long channelId, String status,
                                                            String title, String content, String writer, List<String> tags) {

        Map<String, Object> params = new HashMap<>();
        params.put("channelId", channelId);
        params.put("status", status);
        params.put("title", title);
        params.put("content", content);
        params.put("writer", writer);
        params.put("tags", tags);

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

    public static ExtractableResponse<Response> 질문_목록_조회_요청(final Long questionId) {

        //@formatter:off
        return RestAssured.given()
                                .log().all()
                          .when()
                                .get("/questions/" + questionId)
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

    public static void 응답상태_검증(final ExtractableResponse<Response> response, final HttpStatus ok) {
        assertThat(response.statusCode()).isEqualTo(ok.value());
    }
}

