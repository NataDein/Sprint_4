package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageConfirmOrder {
    private WebDriver driver;
    public PageConfirmOrder (WebDriver driver) {
        this.driver = driver;
    }

    //Элементы страницы
    //Заголовок
    private By headerConfirmOrder = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    //Кнопка "Да"
    private By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Кнопка "Нет"
    private By buttonNo = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text()='Нет']");


    public void clickButtonYes() {
        driver.findElement(buttonYes).click();
    }


    public void waitHeaderConfirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(headerConfirmOrder));
    }


}
