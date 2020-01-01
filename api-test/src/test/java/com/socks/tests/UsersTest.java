package com.socks.tests;

import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;
import com.socks.api.conditions.Condition;
import com.socks.api.conditions.Conditions;
import com.socks.api.conditions.StatusCodeCondition;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.UserRegistrationResponse;
import com.socks.api.services.UserApiService;
import com.sun.javafx.runtime.SystemProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.security.ec.point.ProjectivePoint;

import java.util.Locale;

import static com.socks.api.conditions.Conditions.bodyField;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyString;

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
                .shouldHave(bodyField("id", not(isEmptyString())));

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
        UserRegistrationResponse response = userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .asPojo(UserRegistrationResponse.class);
        response.getId();

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


    }
}
