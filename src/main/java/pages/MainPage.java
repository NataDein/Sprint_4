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
    /** Локатор кнопки принятия куков */
    private final By cookieAcceptButton = By.xpath(".//button[contains(@class, 'App_CookieButton')]");

    /** Локатор кнопки "Заказать" верхняя */
    private final By topOrderButton = By.xpath(".//div[contains(@class, 'Header_Nav')]/button[text()='Заказать']");

    /** Локатор кнопки "Заказать" нижняя */
    private final By bottomOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button[text()='Заказать']");

    /** Блок "Вопросы о важном" */
    private final By questionAboutImportant = By.xpath(".//div[contains(@class, 'Home_SubHeader') and text() = 'Вопросы о важном']");

    /** Локатор контейнера элементов списка "Вопросов о важном" */
    private final By faqSectionLocator = By.xpath(".//div[contains(@class,'Home_FAQ')]");

    /** Локатор кнопки элемента списка "Вопросов о важном". Применяется как часть цепочки к элементу */
    private final By faqItemButtonLocator = By.xpath(".//div[@class='accordion__button']");


    //Методы:

    /** Получить элемент списка "Вопросов о важном" */
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

    /** Получить элемент кнопки в зависимости от переданного типа */
    public WebElement getOrderButton(String buttonType) {
        By orderButton;

        switch (buttonType) {
            case "topOrderButton": orderButton = topOrderButton; break;
            case "bottomOrderButton": orderButton = bottomOrderButton; break;
            default: throw new IllegalArgumentException("Некорректное значение параметра кнопки заказа");
        }

        return driver.findElement(orderButton);
    }

    /** Кликнуть на кнопку "Заказать" */
    public void clickOrderButton(String orderButton) {
        WebElement element = getOrderButton(orderButton);

        element.click();
    }

    /** Перейти к блоку "Вопросы о важном" */
    public void goToQuestionAboutImportant() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionAboutImportant);
    }

    /** Нажать на элемент списка "Вопросов о важном" */
    public void clickImportantQuestionItem(int itemIndex) {
        WebElement faqItemButton = getFAQItem(itemIndex).findElement(faqItemButtonLocator);

        faqItemButton.click();
    }

    /** Получить текст подписи элемента из списка "Вопросов о важном" */
    public String getTextOfLabelFAQItem(int itemIndex) {
        WebElement faqItemContent = getFAQItem(itemIndex).findElement(faqItemButtonLocator);

        return faqItemContent.getText();
    }

    /** Получить текст содержимого элемента из списка "Вопросов о важном" */
    public String getTextContentOfFAQItem(int itemIndex) {
        By faqItemContentLocator = By.xpath(".//div[@class='accordion__panel']");

        WebElement faqItemContent = getFAQItem(itemIndex).findElement(faqItemContentLocator);

        ExpectedCondition<WebElement> isElementInDOM = ExpectedConditions.presenceOfElementLocated(faqItemContentLocator);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(isElementInDOM);

        return faqItemContent.getText();
    }
}
