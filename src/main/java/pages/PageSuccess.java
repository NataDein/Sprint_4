package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageSuccess {
    private WebDriver driver;
    public PageSuccess (WebDriver driver) {
        this.driver = driver;
    }

    //Элементы
    //Заголовок
    private By headerSuccessOrder = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    //Метод проверки успешности заказа
    public boolean checkIsOrderSuccess() {
        return !driver.findElements(headerSuccessOrder).isEmpty();
    }

    public void waitHeaderSuccessOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(headerSuccessOrder));
    }
}
