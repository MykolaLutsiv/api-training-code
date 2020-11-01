package com.socks.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage {

    @Step
    public SelenideElement totalAmount() {
        return $("#orderGrandTotal");
    }

    @Step
    public static ShoppingCartPage open() {
        Selenide.open("/basket.html");
        return Selenide.page(ShoppingCartPage.class);
    }

    @Step
    public ShoppingCartPage deleteItem() {
        $(".fa-trash-o").click();
        return this;
    }


}
