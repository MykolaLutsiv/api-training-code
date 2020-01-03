package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.UserRegistrationResponse;

public class UserApiService extends ApiServices {

    public AssertableResponse registerUser(UserPayload user) {
        return new AssertableResponse(setUp()
                .body(user)
                .when()
                .post("register"));
    }

    public AssertableResponse deleteUser(UserRegistrationResponse response) {
        return new AssertableResponse(setUp()
                .when()
                .delete("customers/" + response.getId()));
    }
}
