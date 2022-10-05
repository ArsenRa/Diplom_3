package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


import java.time.Duration;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;


public class MainPage {
    public static String URL_MAIN = "https://stellarburgers.nomoreparties.site/";

    //Локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    //Локатор кнопки "Личный кабинет"
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement profileButton;

    //Локатор заголовок "Соберите бургер"
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement assembleBurgerHeader;

    //Локатор раздела "Булки"
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']") // "//div[span[text()='Булки']]"
    public static SelenideElement bunTab;

    //Локатор раздела "Соусы"
    @FindBy(how = How.XPATH, using = "//div/span[text()='Соусы']") // "//div[span[text()='Соусы']]"
    public static SelenideElement sauceTab;

    //Локатор раздела "Начинки"
    @FindBy(how = How.XPATH, using = "//div/span[text()='Начинки']") // "//*[text()='Начинки']"
    public static SelenideElement fillingsTab; //private

    //Локатор класса после выбора раздела
    @FindBy(how = How.CLASS_NAME, using = "tab_tab_type_current__2BEPc")
    public SelenideElement ingredientSection;

    public void waitForLoadMainPage() {
        assembleBurgerHeader.shouldBe(visible, Duration.ofSeconds(6));
    }

    @Step("Клик на кнопку Войти в аккаунт")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Клик на кнопку Личный кабинет (неавторизованный пользователь)")
    public LoginPage clickProfileButtonUnauthorizedUser() {
        profileButton.click();
        return page(LoginPage.class);
    }

    @Step("Клик на кнопку Личный кабинет (авторизованный пользователь)")
    public UserPage clickProfileButtonAuthorizedUser() {
        profileButton.shouldBe(visible).click();
        return page(UserPage.class);
    }
    @Step("Клик на раздел Булки")
    public MainPage clickBunsTab() {
        bunTab.click();
        return this;
    }

    @Step("Переход в раздел Булки")
    public boolean isBunsTabDisplayed() {
        return ingredientSection.getText().contentEquals("Булки");
    }
    @Step("Клик на раздел Соусы")
    public MainPage clickSauceTab() {
        sauceTab.click();
        return this;
    }

    @Step("Переход в раздел Соусы")
    public boolean isSauceTabDisplayed() {
        return ingredientSection.getText().contentEquals("Соусы");
    }

    public void checkSection(SelenideElement section) {
        section.parent().shouldHave(cssClass("tab_tab_type_current__2BEPc")).getText(); //cssClass
    }

    @Step("Получение селектора вкладки Соусы в конструкторе")
    public SelenideElement getSouceTab() {
        return sauceTab;
    }

    @Step("Получение селектора вкладки Начинки в конструкторе")
    public SelenideElement getFillingsTab() {
        return fillingsTab;
    }

    @Step("Получение селектора вкладки Булки в конструкторе")
    public SelenideElement getBunTab() {
        return bunTab;
    }



    @Step("Клик на раздел Начинки")
    public MainPage clickFillingsTab() {
        fillingsTab.click();
        return this;
    }

    @Step("переход в раздел Начинки")
    public boolean isFillingsTabDisplayed() {
        return ingredientSection.getText().contentEquals("Начинки");
    }

    @Step("Проверка загрузки страницы")
    public boolean isMainPageOpen() {
        assembleBurgerHeader.shouldBe(visible);
        return url().equals(URL_MAIN);
    }
}
