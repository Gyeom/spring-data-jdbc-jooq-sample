package spring.data.jdbc.channel;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class ChannelAcceptanceStep {

    public static ExtractableResponse<Response> 등록된_채널(String name, String description, String writer) {
        return 채널_생성_요청(name, description, writer);
    }

    public static ExtractableResponse<Response> 채널_생성_요청(String name, String description, String writer) {

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("description", description);
        params.put("writer", writer);

        //@formatter:off
        return RestAssured.given()
                                .log().all()
                                .body(params)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                          .when()
                                .post("/channels")
                          .then()
                                .log().all()
                          .extract();
        //@formatter:on
    }

    public static ExtractableResponse<Response> 채널_목록_조회_요청() {

        //@formatter:off
        return RestAssured.given()
                                .log().all()
                          .when()
                                .get("/channels")
                          .then()
                                .log().all()
                          .extract();
        //@formatter:on
    }

    public static ExtractableResponse<Response> 채널_조회_요청(final Long id) {

        //@formatter:off
        return RestAssured.given()
                                .log().all()
                          .when()
                                .get("/channels/" + id + "/questions")
                          .then()
                                .log().all()
                          .extract();
        //@formatter:on
    }

    public static ExtractableResponse<Response> 채널_수정_요청(
            ExtractableResponse<Response> response, String name, String description) {

        String uri = response.header("Location");
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("description", description);

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
}
