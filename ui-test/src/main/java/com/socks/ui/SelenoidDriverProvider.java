package com.socks.ui;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SelenoidDriverProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String, Object> map = new HashMap<>();
        map.put("enableVNC", true);
        map.put("enableVideo", false);
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "91.0");
        capabilities.setCapability("selenoid:options", map);

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://3.68.221.148:4444/wd/hub").toURL(),
                    capabilities
            );
            driver.manage().window().setSize(new Dimension(1280, 1024));
            driver.setFileDetector(new LocalFileDetector());
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
