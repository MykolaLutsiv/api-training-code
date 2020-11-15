package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payloads.ItemPayload;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;

import java.util.Map;
import java.util.Set;

public class CartApiService extends ApiService {

    @Step
    public AssertableResponse addItemToCart(ItemPayload itemPayload, String cookie) {
        return new AssertableResponse(setUp()
                .cookies("md.sid", cookie)
                .body(itemPayload)
                .when()
                .post("cart"));
    }

    @Step
    public AssertableResponse getCart(String cookie) {
        return new AssertableResponse(setUp()
                .cookies("md.sid", cookie)
                .when()
                .get("cart"));
    }
}
