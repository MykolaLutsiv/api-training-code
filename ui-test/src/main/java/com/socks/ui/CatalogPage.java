package com.socks.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.FluentWait;

import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {

    @Step
    public static CatalogPage open() {
        Selenide.open("/category.html");
        return Selenide.page(CatalogPage.class);
    }

    @Step
    public CatalogPage addItemByIndex(int index) {
        $$("#products .product .buttons a.btn-primary").get(index).click();
        sleep(2000);
        return this;

    }

    @Step
    public String getItemsInCartText() {
        return $("#numItemsInCart").getText();
    }

    @Step
    public ShoppingCartPage goToCart() {
        sleep(500);
        $("#numItemsInCart").click();
        return Selenide.page(ShoppingCartPage.class);
    }
}
