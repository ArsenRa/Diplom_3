import api.UserClient;
import model.User;
import pages.*;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

public class UserPageTest {

    private UserPage userPage;
    private LoginPage loginPage;
    private MainPage mainPage;
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
    @DisplayName("Переход по клику на «Личный кабинет» (авторизированный пользователь)")
    @Description("Переход по клику на «Личный кабинет» (авторизированный пользователь) -> личный кабинет пользователя")
    public void clickProfileButtonAuthorizedUserOpenProfilePageTest() {
        mainPage = page(MainPage.class);
        userPage = page(UserPage.class);
        loginPage = open(LoginPage.URL_LOGIN, LoginPage.class);
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.clickProfileButtonAuthorizedUser();
        assertTrue(userPage.isProfilePageOpen());
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» (не авторизированный пользователь)")
    @Description("Переход по клику на «Личный кабинет» (не авторизированный пользователь) -> страница авторизации")
    public void clickProfileButtonUnauthorizedUserOpenLoginPageTest() {
        loginPage = page(LoginPage.class);
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickProfileButtonUnauthorizedUser();
        assertTrue(loginPage.isLoginPageOpen());
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор» из личного кабинета")
    public void clickConstructorButtonOpenCorrectPageTest() {
        mainPage = page(MainPage.class);
        userPage = page(UserPage.class);
        loginPage = open(LoginPage.URL_LOGIN, LoginPage.class);
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.clickProfileButtonAuthorizedUser();
        userPage.clickConstructorButton();
        assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers из личного кабинета")
    public void clickStellarBurgersOpenCorrectPageTest() {
        mainPage = page(MainPage.class);
        userPage = page(UserPage.class);
        loginPage = open(LoginPage.URL_LOGIN, LoginPage.class);
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.clickProfileButtonAuthorizedUser();
        userPage.clickLogoStellarBurgers();
        assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void userLogoutSuccessTest() {
        mainPage = page(MainPage.class);
        userPage = page(UserPage.class);
        loginPage = open(LoginPage.URL_LOGIN, LoginPage.class);
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.clickProfileButtonAuthorizedUser();
        userPage.clickLogout();
        assertTrue(loginPage.isLoginPageOpen());
    }
}
