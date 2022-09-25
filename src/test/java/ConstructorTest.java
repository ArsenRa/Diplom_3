import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;


public class ConstructorTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
        //Configuration.browser = "firefox"; // запуск тестов в FireFox
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void bunsButtonOpenBunsTabTest() {
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickSauceTab();
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsTabDisplayed());
    }

    @Test
    @DisplayName("Переход к разделу соусы")
    public void sauceButtonOpenSauceTabTest() {
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickSauceTab();
        assertTrue(mainPage.isSauceTabDisplayed());
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void fillingsButtonOpenFillingsTabTest() {
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickFillingsTab();
        assertTrue(mainPage.isFillingsTabDisplayed());
    }
}
