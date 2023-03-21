import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class OrderApi extends Main {

    private static final String COURIER_URI = BASE_URI + "orders/";

    @Step("Creating an order")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseReqSpec())
                .body(order)
                .when()
                .post(COURIER_URI)
                .then();
    }
    @Step("Getting an order")
    public ValidatableResponse getOrders() {
        return given()
                .spec(getBaseReqSpec())
                .when()
                .get(COURIER_URI)
                .then();
    }

}
