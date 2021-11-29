package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.socks.api.payloads.ItemPayload;
import com.socks.api.services.CartApiService;
import com.socks.api.services.UserApiService;
import com.socks.ui.CatalogPage;
import com.socks.ui.ShoppingCartPage;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Selenide.sleep;

public class ShoppingCartTest extends BaseUiTest{

    @AfterMethod
    public void tearDown() {
        Selenide.clearBrowserCookies();
    }

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

        String itemsInCartText = new CatalogPage().getItemsInCartText();
        Assert.assertEquals(itemsInCartText, String.format("%s item(s) in cart", 1));

        ShoppingCartPage.open()
                .deleteItem()
                .totalAmount().shouldHave(Condition.exactText("$0.00"));

    }

    @Test
    public void testUserCanDeleteItemFromCart2() {
        CatalogPage.open();

        String cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("md.sid").getValue();
        ItemPayload item = new ItemPayload()
                .id("03fef6ac-1896-4ce8-bd69-b798f85c6e0b");

        cartApiService.addItemToCart(item, cookie);
        cartApiService.getCart(cookie);

        ShoppingCartPage.open()
                .deleteItem()
                .totalAmount().shouldHave(Condition.exactText("$0.00"));

    }
}
