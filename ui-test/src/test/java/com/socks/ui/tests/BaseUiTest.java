package com.socks.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;
import com.socks.api.services.CartApiService;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.util.Locale;


public abstract class BaseUiTest {

    ProjectConfig config = ConfigFactory.create(ProjectConfig.class,  System.getProperties());
    public final Faker faker = new Faker(new Locale(config.locale()));
    public final UserApiService userApiService = new UserApiService();
    public final CartApiService cartApiService = new CartApiService();


    @BeforeSuite
    public void setUp() {

        RestAssured.baseURI = config.baseUrl();
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = "com.socks.ui.SelenoidDriverProvider";
//        Configuration.browser = WebDriverRunner.CHROME;
//        Configuration.pollingInterval = 800;

    }


    @DataProvider(name = "loginData")
    public Object[][] dds() {
        return new String[][] { {"1", "2"}, {"2", "pass"}};
    }

    protected <T> T at(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }
}
