package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageAboutRent {
    private WebDriver driver;
    public PageAboutRent (WebDriver driver) {
        this.driver = driver;
    }

    //Поля:

    //Заголовок страницы
    private By headerAboutRent = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Про аренду']");
    //Когда привезти самокат
    private By dateOrder = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Срок аренды (поле)
    private By rentalPeriod = By.xpath(".//div[@class='Dropdown-control']");
    //Срок аренды (значения в поле- количество дней аренды)
    private By ammountOfRentalDays = By.xpath(".//div[@class='Dropdown-option']"); //пока возвращает семь полей, надо подумать, позже, что с этим делать
    //Цвет самоката: черный
    private By blackScooter = By.xpath(".//input[@id='black']");
    //Цвет самоката: серый
    private By greyScooter = By.xpath(".//input[@id='grey']");
    //Комментарий для курьера
    private By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");


    //Локатор кнопки "Заказать"
    private By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //Методы:

    //Заполнение поля "Когда привезти самокат"
    public void setDateOrder (String dateValue) {
        WebElement dateOrderField = driver.findElement(dateOrder);

        dateOrderField.sendKeys(dateValue);
        dateOrderField.sendKeys(Keys.ESCAPE);
    }

    //Заполнение поля "Срок аренды"
    public void setRentalPeriod (String valueRentalPeriod) {
        driver.findElement(rentalPeriod).click();
        driver.findElement(By.xpath(".//div[contains(@class, 'Dropdown-option') and text()='" + valueRentalPeriod + "']")).click();

    }

    //Заполнение цвета
    public void setColour (String colourValue) {
        driver.findElement(By.xpath(".//input[@type='checkbox' and @id='" + colourValue + "']")).click();
    }

    //Заполнение комментария
    public void setComment (String commentValue) {
        driver.findElement(comment).sendKeys(commentValue);
    }


    //Клик по кнопке "Заказать"
    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    //Заполнение формы
    public void fillOutForm (String dateValue, String valueRentalPeriod, String colourValue, String commentValue) {
        setDateOrder(dateValue);
        setRentalPeriod(valueRentalPeriod);
        setColour(colourValue);
        setComment(commentValue);
    }

    //Вэйтер. Ждем пока появится заголовок "Про аренду"
    public void waitHeaderAboutRent() {
       new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(headerAboutRent));
    }







}
