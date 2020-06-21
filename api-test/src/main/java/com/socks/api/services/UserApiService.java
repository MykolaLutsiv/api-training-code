package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.UserRegistrationResponse;
import io.qameta.allure.Step;

public class UserApiService extends ApiService {

    @Step
    public AssertableResponse registerUser(UserPayload user) {
        return new AssertableResponse(setUp()
                .body(user)
                .when()
                .post("register"));
    }

    public AssertableResponse deleteUser(UserRegistrationResponse user) {
        return new AssertableResponse(setUp()
                .when()
                .delete("customers/" + user.getId()));
    }

    public AssertableResponse loggedUser() {
        return new AssertableResponse(setUp()
                .when()
                .get("login"));
    }

    public AssertableResponse getCards() {
        return new AssertableResponse(setUp()
        .when()
        .get("cards"));
    }
}
