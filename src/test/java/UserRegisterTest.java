import api.UserClient;
import dto.UserCreate;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.RegisterPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

import static com.codeborne.selenide.Condition.visible;
import static org.junit.Assert.assertTrue;

public class UserRegisterTest {
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private UserClient userClient;
    private User user;
    private String accessToken;
    private String refreshToken;

    @Before
    public void setUp(){
        userClient = new UserClient();
        user = UserCreate.getRandomUser();
        //userClient.create(user);

        /*ValidatableResponse loginResponse = userClient.login(user);
        accessToken = loginResponse.log().all().extract().path("accessToken"); //.toString()
        refreshToken = loginResponse.extract().path("refreshToken"); //.toString()*/

        loginPage = page(LoginPage.class);
        registerPage = open(RegisterPage.URL_REGISTER, RegisterPage.class);
    }

    @After
    public void tearDown(){
        getWebDriver().quit();

        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрацию")
    public void loginUserSuccessTest() {
        registerPage.fillInputsFieldsAndRegister(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(loginPage.isLoginPageOpen());
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void loginUserInvalidPassTest() {
        registerPage.fillInputsFieldsAndRegister(user.getName(), user.getEmail(), RandomStringUtils.randomAlphanumeric(5));
        User credentials = new User(user.getEmail(), user.getPassword(), null);
        //accessToken = userClient.login(credentials);
        registerPage.getInvalidPasswordErrorMessage().shouldBe(visible);
        registerPage.getInvalidPasswordErrorText().equals("Некорректный пароль");
    }
}


