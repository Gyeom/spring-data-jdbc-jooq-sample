package spring.data.jdbc.user;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class UserAcceptanceStep {

    public static ExtractableResponse<Response> 등록된_유저(String id, String name) {
        return 유저_생성_요청(id, name);
    }

    public static ExtractableResponse<Response> 유저_생성_요청(String id, String name) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);

        //@formatter:off
        return RestAssured.given()
                                .log().all()
                                .body(params)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                          .when()
                                .post("/users")
                          .then()
                                .log().all()
                          .extract();
        //@formatter:on
    }
}
