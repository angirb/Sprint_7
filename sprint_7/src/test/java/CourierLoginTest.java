import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static java.net.HttpURLConnection.*;
import static org.junit.Assert.*;

public class CourierLoginTest {

    private Courier courier;
    private CourierApi courierApi;
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
        courier = new Courier("Sharingan", "konoha1234", "Uchiha");

    }


    @Test
    @DisplayName("Can login with valid data")
    @Description("Check if courier can login with existing account")
    public void testLoginCourier() {
        Courier courier = new Courier("Sharingan", "konoha1234", "Uchiha");
        ValidatableResponse response = new CourierApi().login(courier);
        int statusCode = response.extract().statusCode();
        ValidatableResponse loginResponse = courierApi.login(courier);
        courierId = loginResponse.extract().path("id");

        Assert.assertEquals("Successful login", HTTP_OK, statusCode);
        assertTrue("Courier ID", courierId != 0);



    }

    @Test
    @DisplayName("Login without firstName")
    @Description("Check if client can login without entering firstName")
    public void testLoginCourierWithOutFirstName() {
        Courier courier = new Courier("Sharingan", "konoha1234", " ");
        ValidatableResponse response = new CourierApi().login(courier);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Successful login", HTTP_OK, statusCode);

    }
    @Test
    @DisplayName("Login with data that never registered")
    @Description("Check if can register with account information that does not exist")
    public void testLoginCourierThatDoesNotExist() {
        Courier courier = new Courier("CourierDoesNotExist", "konoha1234", "Random");
        ValidatableResponse response = new CourierApi().login(courier);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Учетная запись не найдена", HTTP_NOT_FOUND, statusCode);
        System.out.println("Учетная запись не найдена");

    }
    @Test
    @DisplayName("Login with wrong password")
    @Description("Check if it possible to login with wrong password but correct account name")
    public void testLoginCourierWithWrongPassword() {
        Courier courier = new Courier("Sharingan", "konoha", "Uchiha");
        ValidatableResponse response = new CourierApi().login(courier);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Password is incorrect", HTTP_NOT_FOUND, statusCode);
        System.out.println("Учетная запись не найдена");

    }
}
