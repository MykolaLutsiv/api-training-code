package com.socks.tests;

import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;

import java.util.Locale;

public class BaseTest {

    public final UserApiService userApiService = new UserApiService();
    public Faker faker;


    @BeforeClass
    public void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        faker = new Faker(new Locale(config.locale()));
        RestAssured.baseURI = config.baseUrl();
        RestAssured.defaultParser = Parser.JSON;

    }


}
