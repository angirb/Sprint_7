import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.*;

public class CourierCreateTest {

    private CourierApi courierApi;
    private Courier courier;
    private CourierAccount courierAccount;
    private int courierId;
    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    @Before
    public void setUp() {
        courierApi = new CourierApi();
    }

    @After
    public void cleanData() {
        courierApi.delete(courierId);
    }


    @Test
    @DisplayName("create courier with valid information")
    @Description("create courier with normal data")
    public void courierCanBeCreatedWithValidData() {
        Courier courier = new Courier("RockLee3", "konohasempu", "konohayouth");

        courierApi.create(courier)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .assertThat()
                .body("ok", is(true));
    }

    @Test
    @DisplayName("create client with random data")
    @Description("generate random symbols to create courier")
    public void courierCanBeCreatedWithRandomGenerator() {
        Courier courier = CourierGenerator.getRandom();

        courierApi.create(courier)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", is(true));

    }

    @Test
    public void courierCannotBeCreatedWithExistingAccount() {
        Courier courier = new Courier("Sharingan", "konoha123456", "Uchiha");

        courierApi.create(courier)
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .and()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}