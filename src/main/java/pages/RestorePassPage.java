package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class RestorePassPage {
    public final static String URL_FORGOT_PASSWORD = "https://stellarburgers.nomoreparties.site/forgot-password";

    //Локатор ссылки "Войти"
    @FindBy(xpath = "//a[text()='Войти']")
    private SelenideElement loginButton;

    @Step("Клик на ссылку войти на странице восстановления пароля")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }
}
