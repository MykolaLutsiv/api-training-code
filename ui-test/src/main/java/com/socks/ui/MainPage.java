package com.socks.ui;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    @Step
    public static MainPage open() {
        Selenide.open("/");
        return new MainPage();
    }

    @Step
    public void loginAs(String username, String password) {
        $("#login").click();
        $("input#username-modal").setValue(username);
        $("input#password-modal").setValue(password);
        $(".modal-content .text-center").$(Selectors.byText("Log in")).click();

    }
}
