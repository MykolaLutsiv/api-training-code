package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.UserRegistrationResponse;
import io.qameta.allure.Step;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.config.LogConfig;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookie;

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
                .basic("user", "password")
                .when()
                .get("login")
                .getCookies();
    }

    @Step
    public AssertableResponse customerById(String id) {
        return new AssertableResponse(setUp()
                .when()
                .get("customers/" + id));
    }


    @Step
    public AssertableResponse getCards() {
        return new AssertableResponse(setUp()
        .when()
        .get("cards"));
    }

    @Step
    public AssertableResponse getOrders() {
        return new AssertableResponse(setUp()
                .cookies(login())
                .when()
                .get("orders"));
    }
}
