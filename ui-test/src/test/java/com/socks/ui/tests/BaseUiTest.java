package com.socks.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;

import java.util.Locale;


public abstract class BaseUiTest {

    ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    public final Faker faker = new Faker(new Locale(config.locale()));

    static {
//        Configuration.browser = WebDriverRunner.CHROME;
    }

    @BeforeSuite
    public void setUp() {

        RestAssured.baseURI = config.baseUrl();
        Configuration.baseUrl = config.baseUrl();
//        Configuration.pollingInterval = 800;

    }

    protected <T> T at(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }
}
