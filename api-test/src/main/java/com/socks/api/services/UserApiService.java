package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payloads.CardPayload;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.CardCreateResponse;
import com.socks.api.responses.UserRegistrationResponse;
import io.qameta.allure.Step;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.config.LogConfig;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApiService extends ApiService {

    @Step
    public AssertableResponse registerUser(UserPayload user) {
        return new AssertableResponse(setUp()
                .body(user)
                .when()
                .post("register"));
    }

    @Step
    public AssertableResponse deleteUser(String id) {
        return new AssertableResponse(setUp()
                .when()
                .delete("customers/" + id));
    }

    @Step
    public Map<String, String> login() {
        return setUp()
                .auth()
                .preemptive()
                .basic("mykola", "12345")
                .when()
                .get("login")
                .getCookies();
    }

    @Step
    public AssertableResponse login(String username, String password) {
        return new AssertableResponse(setUp()
                .auth()
                .preemptive()
                .basic(username, password)
                .when()
                .get("login"));
    }

    @Step
    public AssertableResponse customerById(String id) {
        return new AssertableResponse(setUp()
                .when()
                .get("customers/" + id));
    }

    @Step
    public AssertableResponse addItemToCartById(String itemId) {
        return new AssertableResponse(setUp()
                .cookies(login())
                .body("{id: " + itemId + "}")
                .when()
                .post("cart"));
    }


    @Step
    public AssertableResponse getCards() {
        return new AssertableResponse(setUp()
                .when()
                .get("cards"));
    }

    @Step
    public AssertableResponse getCardById(String id) {
        return new AssertableResponse(setUp()
                .when()
                .get("cards/" + id));
    }

    @Step
    public AssertableResponse deleteCard(String id) {
        return new AssertableResponse(setUp()
                .when()
                .delete("cards/" + id));
    }

    @Step
    public AssertableResponse createCard(CardPayload card) {
        return new AssertableResponse((Response) setUp()
                .cookies(login())
                .body(card)
                .when()
                .post("cards"));
    }


    @Step
    public AssertableResponse getOrders() {
        return new AssertableResponse(setUp()
                .cookies(login())
                .when()
                .get("orders"));
    }

}
