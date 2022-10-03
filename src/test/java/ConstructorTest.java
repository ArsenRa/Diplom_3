import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static pages.MainPage.bunTab;
import static pages.MainPage.fillingsTab;
import static pages.MainPage.sauceTab;


public class ConstructorTest {
    private MainPage mainPage;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        getWebDriver().quit();
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void bunsButtonOpenBunsTabTest() {
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.waitForLoadMainPage();
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        mainPage.checkSection(bunTab);

    }

    @Test
    @DisplayName("Переход к разделу соусы")
    public void sauceButtonOpenSauceTabTest() {
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickSauceTab();
        mainPage.checkSection(sauceTab);
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void fillingsButtonOpenFillingsTabTest() {
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickFillingsTab();
        mainPage.checkSection(fillingsTab);
    }
}
