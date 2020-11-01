package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.socks.api.services.UserApiService;
import com.socks.ui.CatalogPage;
import com.socks.ui.ShoppingCartPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class ShoppingCartTest extends BaseUiTest{
    private final UserApiService userApiService = new UserApiService();

    @Test
    public void testUserCanAddItemFromCatalog() {
        CatalogPage.open()
                .addItemByIndex(0)
                .goToCart();

        at(ShoppingCartPage.class)
                .totalAmount().shouldHave(Condition.exactText("$104.98"));
    }

    @Test
    public void testUserCanDeleteItemFromCart() {
        CatalogPage.open()
                .addItemByIndex(0);

        ShoppingCartPage.open()
                .deleteItem()
                .totalAmount().shouldHave(Condition.exactText("$0.00"));

    }
}
