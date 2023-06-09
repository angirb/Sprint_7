import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierApi extends Main {

    private static final String COURIER_URI = BASE_URI + "courier/";

    @Step("Create courier {courier}")
    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getBaseReqSpec())
                .body(courier)
                .when()
                .post(COURIER_URI)
                .then();
    }
    @Step("Login as {Courier}")
    public ValidatableResponse login(Courier courier) {
        return given()
                .spec(getBaseReqSpec())
                .body(courier)
                .when()
                .post(COURIER_URI+ "login/")
                .then();
    }
    @Step("Delete courier {id}")
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getBaseReqSpec())
                .when()
                .delete(COURIER_URI + id)
                .then();
    }
}
