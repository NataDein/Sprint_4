package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;

    public MainPage (WebDriver driver) {
        this.driver = driver;
    }

    //Элементы:
    /**  */
    private final By cookieAcceptButton = By.xpath(".//button[contains(@class, 'App_CookieButton')]");

    //Кнопка "Заказать" верхняя
    private final By topOrder = By.xpath(".//button[@class='.//button[@class='Button_Button__ra12g']");

    //Кнопка "Заказать" нижняя
    private final By buttomOrder = By.xpath(".//button[@class='.//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");

    //Блок "Вопросы о важном"
    private final By questionAboutImportant = By.xpath(".//div[contains(@class, 'Home_SubHeader') and text() = 'Вопросы о важном']");

    /** Контейнер элементов списка "Вопросов о важном" */
    private final By faqSectionLocator = By.xpath(".//div[contains(@class,'Home_FAQ')]");
    /** Локатор кнопки элемента списка "Вопросов о важном". Применяется как часть цепочки к элементу */
    private final By faqItemButtonLocator = By.xpath(".//div[@class='accordion__button']");


    //Методы:

    private WebElement getFAQItem(int itemIndex) {
        By faqItemLocator = By.xpath(".//div[@class='accordion__item']");

        WebElement faqSection = driver.findElement(faqSectionLocator);
        List<WebElement> faqItemsList = faqSection.findElements(faqItemLocator);

        return faqItemsList.get(itemIndex);
    }

    /** Принять обработку cookie */
    public void acceptCookie() {
        driver.findElement(cookieAcceptButton).click();
    }

    //Кликнуть на кнопку "Заказать"
    public void clickOrderButton(By orderButton) {
        WebElement element = driver.findElement(orderButton);

        element.click();
    }

    //Перейти к блоку "Вопросы о важном"
    public void goToQuestionAboutImportant() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionAboutImportant);
    }

    public void clickImportantQuestionItem(int itemIndex) {
        WebElement faqItemButton = getFAQItem(itemIndex).findElement(faqItemButtonLocator);

        faqItemButton.click();
    }

    public String getTextOfLabelFAQItem(int itemIndex) {
        WebElement faqItemContent = getFAQItem(itemIndex).findElement(faqItemButtonLocator);

        return faqItemContent.getText();
    }

    public String getTextContentOfFAQItem(int itemIndex) {
        By faqItemContentLocator = By.xpath(".//div[@class='accordion__panel']");

        WebElement faqItemContent = getFAQItem(itemIndex).findElement(faqItemContentLocator);

        ExpectedCondition<WebElement> isElementInDOM = ExpectedConditions.presenceOfElementLocated(faqItemContentLocator);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(isElementInDOM);

        return faqItemContent.getText();
    }
}
