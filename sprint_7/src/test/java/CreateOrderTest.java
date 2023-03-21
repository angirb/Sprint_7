import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    private OrderApi orderApi;
    private Order order;
    private int orderTrack;
    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }


    public CreateOrderTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    @Parameterized.Parameters

    public static Object[][] getData() {
        return OrderTestData.TEST_DATA;
    }
    @Before
    public void setUp() {
        orderApi = new OrderApi();
        order = new Order(
                firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color
        );
    }
    @Test
    @DisplayName("Order can be created successfully")
    @Description("Checking status code")
    public void createOrderTests() {
        ValidatableResponse createResponse = orderApi.create(order);

        int statusCode = createResponse.extract().statusCode();
        int track = createResponse.extract().path("track");
        assertEquals("Incorrect status code", HTTP_CREATED, statusCode);
        assertNotNull("Order did not arrive", track);
    }
    @Test
    @DisplayName("Check order list")
    @Description("Check if created order exist")

    public void getOrderList() {
        ValidatableResponse createResponse = orderApi.getOrders();
        int statusCode = createResponse.extract().statusCode();
        ArrayList order = createResponse.extract().path("orders");
        assertEquals("Incorrect status code", HTTP_OK, statusCode);
        assertFalse("Order is empty", order.isEmpty());
    }
}
