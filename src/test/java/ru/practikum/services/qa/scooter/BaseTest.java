package ru.practikum.services.qa.scooter;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    public static WebDriver driver;

    @Before
    public void init() {
        driver = getDriver(System.getenv("BROWSER"));

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    private WebDriver getDriver(String browser) {
        if (browser == null) throw new IllegalArgumentException("Browser type is not provided");

        switch (browser.toLowerCase()) {
            case "chrome": return new ChromeDriver();
            case "firefox": return new FirefoxDriver();
            default: throw new IllegalArgumentException("Unsupported browser type");
        }
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
