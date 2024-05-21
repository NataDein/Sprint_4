package ru.practikum.services.qa.scooter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pages.*;


@RunWith(Parameterized.class)
public class OrderingScooterTest extends BaseTest {
    private final By orderButton;   // Кнопка заказа (верхняя/нижняя)
    private final String name;          // Имя заказчика
    private final String surname;       // Фамилия заказчика
    private final String address;       // Адрес доставки
    private final String phone;   // Телефон
    private final String dateOrder; //Когда привезти самокат
    private final String rentalPeriod;  // Период аренды из выпадающего меню
    private final String colour;        // Цвет самоката
    private final String comment;       // Комментарий



    public OrderingScooterTest(
            By orderButton,
            String name,
            String surname,
            String address,
            String phone,
            String dateOrder,
            String rentalPeriod,
            String colour,
            String comment
    ) {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.rentalPeriod = rentalPeriod;
        this.dateOrder = dateOrder;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {By.xpath(".//div[contains(@class, 'Header_Nav')]/button[text()='Заказать']"), "Алиса", "Нагорных", "Ленинский пр.дом 187", "+78005353535", "01.06.2024", "сутки",
                        "black", "1"},
                {By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button[text()='Заказать']"), "Зара", "Эчендемовская", "ул. Пушкина, дом 1 квартира 5",
                        "+79816007080", "30.05.2024", "трое суток", "grey",
                        "Привет. Спишь?"},
        };
    }

    @Test
    public void orderingScooterWithValidData() {
        MainPage mainPage = new MainPage(driver); // не знаю, что его не устраивает, но и не знаю смысл данного "драйвер" в этой записи
        PageOrderFor pageOrderFor = new PageOrderFor(driver);
        PageAboutRent pageAboutRent = new PageAboutRent(driver);
        PageConfirmOrder pageConfirmOrder = new PageConfirmOrder(driver);
        PageSuccess pageSuccess = new PageSuccess(driver);

        //Открываем главную страницу и кликаем на кнопку "Заказать"
        mainPage.acceptCookie();
        mainPage.clickOrderButton(orderButton);

        // Ждём появления страницы "Для кого самокат"
        pageOrderFor.waitFormInTheDOM();

        //Заполняем страничку "Для кого самокат" и кликаем "Далее"
        pageOrderFor.fillOutForm(name,surname,address,phone);
        pageOrderFor.clickButtonNext();

        //Заполняем страничку "Про аренду" и кликаем "Заказать"
        pageAboutRent.waitHeaderAboutRent();
        pageAboutRent.fillOutForm(dateOrder, rentalPeriod, colour, comment);
        pageAboutRent.clickButtonOrder();

        //Подтверждаем заказ
        pageConfirmOrder.waitHeaderConfirmOrder();
        pageConfirmOrder.clickButtonYes();

        //Сравниваем, что в итоговом окне есть заголовок "Заказ оформлен"
        pageSuccess.waitHeaderSuccessOrder();
        Assert.assertTrue("Окно с заголовком \"Заказ оформлен\" отсутствует", pageSuccess.checkIsOrderSuccess());
    }
}
