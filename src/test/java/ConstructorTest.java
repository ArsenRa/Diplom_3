import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;


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
        mainPage.clickSauceTab();
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        //assertTrue(mainPage.isBunsTabDisplayed());
        Assert.assertEquals("Булки", mainPage.bunTab.getAttribute("textContent"));
        //Assert.assertEquals("Булки", mainPage.getBunTab().$(By.cssSelector(".text")).getText());
    }

    @Test
    @DisplayName("Переход к разделу соусы")
    public void sauceButtonOpenSauceTabTest() {
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickSauceTab();
        //mainPage.checkSection(mainPage.sauceTab);
        //assertTrue(mainPage.isSauceTabDisplayed());
        Assert.assertEquals("Соусы", mainPage.sauceTab.getAttribute("textContent"));
        //Assert.assertEquals("Соусы", mainPage.getSouceTab().$(By.cssSelector(".text")).getText());
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void fillingsButtonOpenFillingsTabTest() {
        mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickFillingsTab(); //.shouldBe(visible, .Duration.ofSeconds(30)
        //mainPage.checkSection(mainPage.fillingsTab);
        //assertTrue(mainPage.isFillingsTabDisplayed());
        Assert.assertEquals("Начинки", mainPage.fillingsTab.getAttribute("textContent"));
        //Assert.assertEquals("Начинки", mainPage.getFillingsTab().getText());
    }
}
