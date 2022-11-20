package spring.data.jdbc.answer;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class AnswerAcceptanceStep {
    public static ExtractableResponse<Response> 등록된_답변(Long questionId, String content,
                                                       String writer) {
        return 답변_생성_요청(questionId, content, writer);
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
                                .post("/answers")
                          .then()
                                .log().all()
                          .extract();
        //@formatter:on
    }
}
