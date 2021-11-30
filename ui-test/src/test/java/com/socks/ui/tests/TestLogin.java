package com.socks.ui.tests;


import com.codeborne.selenide.Condition;
import com.socks.api.payloads.UserPayload;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.testng.annotations.Test;

import static com.socks.api.conditions.Conditions.statusCode;

public class TestLogin extends BaseUiTest {


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
