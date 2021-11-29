package com.socks.ui.tests;


import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import com.socks.api.conditions.Conditions;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class TestLogin extends BaseUiTest{


    @Test(dataProvider = "loginData")
    public void userCanLoginWithValidCredentials(String login, String password) {

        System.out.println(login + password);
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password());
        // expect
        userApiService.registerUser(user)
                .shouldHave(statusCode(200));

        MainPage.open()
                .loginAs(user.username(), user.password());

        LoggedUserPage loggedUserPage = at(LoggedUserPage.class);
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as"));
    }
}
