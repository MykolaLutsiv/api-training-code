package com.socks.tests;

import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;

import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.UserRegistrationResponse;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static com.socks.api.conditions.Conditions.bodyField;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.*;

public class UsersTest {

    private final UserApiService userApiService = new UserApiService();
    private Faker faker;

    @BeforeClass
    public void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        faker = new Faker(new Locale(config.locale()));
        RestAssured.baseURI = config.baseUrl();
    }


    @Test
    public void  testCanRegisterNewUser() {
        // given
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@mail.com")
                .password("test123");

        // expect
        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyOrNullString())));

    }

//    @Test
//    public void  testLogin() {
//        // given
//        UserPayload user = new UserPayload()
//                .username(faker.name().username())
//                .email("test@mail.com")
//                .password("test123");
//
//        // expect
//        UserRegistrationResponse userRespone = userApiService.registerUser(user)
//                .shouldHave(statusCode(200))
//                .shouldHave(bodyField("id", not(isEmptyOrNullString())))
//                .asPojo(UserRegistrationResponse.class);
//        userApiService.login(user.username(), user.password())
//                .shouldHave(statusCode(200));
//        CustomerResponse customerResponse = userApiService.customerById(userRespone.getId())
//                .asPojo(CustomerResponse.class);
//        Assert.assertEquals(customerResponse.getUsername(), user.username());
//
//
//    }

    @Test
    public void  testGetOrders() {

        userApiService.getOrders().shouldHave(statusCode(201));


    }

    // bY MEEEEEEEE
    @Test
    public void testCanDeleteUser() {
        // given
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@mail.com")
                .password("test123");

        // expect
        UserRegistrationResponse response = userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .asPojo(UserRegistrationResponse.class);

        userApiService.deleteUser(response.getId())
                .shouldHave(statusCode(200));
    }

    // SAME TEST AS ABOVE BUT WITH POJO CLASS
    @Test
    public void testCanRegisterNewUser2() {
        // given
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@mail.com")
                .password("test123");

        // expect
        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .asPojo(UserRegistrationResponse.class);
//        Assert.assertNotNull(response.getId());
//        response.getId();
    }

    @Test
    public void testCanNotRegisterSameUser() {
//        UserPayload user = new UserPayload("test123", "test@mail.com",
//                RandomStringUtils.randomAlphanumeric(6));
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@mail.com")
                .password("test123");

        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyString())));

        userApiService.registerUser(user)
                .shouldHave(statusCode(500));
//                .shouldHave(bodyField())

    }

    @Test
    public void getCards() {

        userApiService.getCards()
                .shouldHave(statusCode(200));
     }

}

