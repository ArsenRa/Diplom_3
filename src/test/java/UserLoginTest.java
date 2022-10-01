import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import pages.RestorePassPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

public class UserLoginTest {

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private RestorePassPage restorePassPage;
    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp(){
        userClient = new UserClient();
        user = User.getRandomUser();
        userClient.create(user);

        ValidatableResponse loginResponse = userClient.login(user);
        accessToken = loginResponse.log().all().extract().path("accessToken"); //.toString()

    }

    @After
    public void tearDown(){
        getWebDriver().quit();

        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginClickLogInButtonOnMainPageTest() {
        loginPage = page(LoginPage.class);
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickLoginButton();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginClickLogInButtonOnProfilePageTest() {
        loginPage = page(LoginPage.class);
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickProfileButtonUnauthorizedUser();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginClickLoginButtonOnRegistrationPageTest() {
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        registerPage = open(RegisterPage.URL_REGISTER, RegisterPage.class);
        registerPage.loginButtonClick();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginClickLoginButtonOnForgotPasswordPageTest() {
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        restorePassPage = open(RestorePassPage.URL_FORGOT_PASSWORD, RestorePassPage.class);
        restorePassPage.clickLoginButton();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isMainPageOpen());
    }
}


