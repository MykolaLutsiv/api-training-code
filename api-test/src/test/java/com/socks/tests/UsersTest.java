package com.socks.tests;

import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;

import com.socks.api.payloads.CardPayload;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.CardsResponse.CardItem;
import com.socks.api.responses.CardsResponse.CardsResponse;
import com.socks.api.responses.CardCreateResponse;
import com.socks.api.responses.UserRegistrationResponse;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
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
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void  testCanRegisterNewUser() {
        // given
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password());

        // expect
        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", notNullValue()));

    }

    @Test
    public void  testGetOrders() {
        userApiService.getOrders().shouldHave(statusCode(201));
    }


    @Test
    public void testCanDeleteUser() {
        // given
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password());

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
                .email(faker.internet().emailAddress())
                .password(faker.internet().password());

        // expect
        UserRegistrationResponse response = userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .asPojo(UserRegistrationResponse.class);
        Assert.assertNotNull(response.getId());
    }

    @Test
    public void testCanNotRegisterSameUser() {

        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password());

        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", notNullValue()));

        userApiService.registerUser(user)
                .shouldHave(statusCode(500));

    }

    @Test
    public void getCards() {

        userApiService.getCards()
                .shouldHave(statusCode(200))
                .asPojo(CardsResponse.class);
     }

    @Test
    public void createCard() {
        CardPayload card = new CardPayload()
                .ccv("11/11")
                .expires("2024")
                .longNum("0000123")
                .userID("NoMatterqwe");
        CardCreateResponse cardCreateResponse = userApiService.createCard(card)
                .shouldHave(statusCode(200))
                .asPojo(CardCreateResponse.class);
        Assert.assertNotNull(cardCreateResponse.getId());
    }

     @Test
    public void getCreatedCard() {
         CardPayload card = new CardPayload()
                 .ccv(faker.internet().domainWord())
                 .expires("20/2030")
                 .longNum("0011")
                 .userID(faker.internet().uuid());

         CardCreateResponse cardCreateResponse = userApiService.createCard(card)
                 .shouldHave(statusCode(200))
                 .asPojo(CardCreateResponse.class);

         CardItem cardItem = userApiService.getCardById(cardCreateResponse.getId())
                 .shouldHave(statusCode(200))
                 .asPojo(CardItem.class);
     }


    @Test
    public void deleteCard() {
        CardPayload card = new CardPayload()
                .ccv("11/11")
                .expires("2024")
                .longNum("0000123")
                .userID("NoMatterqwe");
        CardCreateResponse cardCreateResponse = userApiService.createCard(card)
                .shouldHave(statusCode(200))
                .asPojo(CardCreateResponse.class);
        userApiService.deleteCard(cardCreateResponse.getId())
                .shouldHave(statusCode(200));
    }

}

