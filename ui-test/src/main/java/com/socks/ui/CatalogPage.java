package com.socks.ui;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

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
        return this;
    }

    @Step
    public ShoppingCartPage goToCart() {
        sleep(200);
        $("#numItemsInCart").click();
        return Selenide.page(ShoppingCartPage.class);
    }
}
