package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageOrderFor {

    private WebDriver driver;
    public PageOrderFor (WebDriver driver) {
        this.driver = driver;
    }

    //Поля:

    //Заголовок страницы
//    private By headerOrderFor = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Для кого самокат']");
    private By headerOrderFor = By.xpath(".//div[contains(@class, 'Order_Header') and text()='Для кого самокат']");
    //Имя
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Фамилия
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Адрес
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Станция метро
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");

    //Телефон
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка "Далее"
    private By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");

    //Методы

    //Заполнить поле name
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    //Заполнить поле surname
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    //Заполнить поле addressField
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    // Заполнить поле metroStationField
    public void setMetroStation() {
        driver.findElement(metroStationField).click();
        By listItem = By.xpath(".//div[@class='select-search__select']/ul/li[1]");
        driver.findElement(listItem).click();
    }

    //Заполнить поле phoneField
    public void setPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    //Кликнуть по кнопке "Далее"
    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    /** Ожидать открытия страницы "Для кого самокат" */
    public void waitFormInTheDOM() {
        ExpectedCondition<WebElement> isElementInDOM = ExpectedConditions.presenceOfElementLocated(headerOrderFor);

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(isElementInDOM);
    }

    //Заполнить форму
    public void fillOutForm (String name, String surname, String address, String phone) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStation();
        setPhoneField(phone);
    }

}
