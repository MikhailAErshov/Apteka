package com.example.apteka;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest extends WebTest{

    AptekaMainPage aptekaMainPage = new AptekaMainPage();
    AptekaCataloguePage aptekaCataloguePage = new AptekaCataloguePage();
    AptekaSearchPage aptekaSearchPage = new AptekaSearchPage();
    AptekaBasketPage aptekaBasketPage = new AptekaBasketPage();

    @BeforeEach
    public void setUp() {
        open("https://aptekaeconom.com/");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "103006"));
        refresh();
        aptekaMainPage.confirmRegion.shouldNotBe(visible);
    }

    @AfterEach
    public void setDown() {
        Selenide.webdriver().driver().getWebDriver().manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Переход к категории товара")
    public void shouldTheDisplayOfAProductCategory() {
        step("Навести курсор на одну из категорий и нажать на подкатегорию", () -> {
            aptekaMainPage.checkTheTransitionToACategoryWithAProduct();
        });

        step("Проверить, что переход успешен и корректно отображается соответствующая подкатегория", () -> {
            aptekaCataloguePage.pagetitle.shouldBe(visible);
            aptekaCataloguePage.pagetitle.shouldHave(Condition.text(Variables.searchVariables));
        });

        step("Проверить, что хлебные крошки отображаются корректно", () -> {
            aptekaCataloguePage.breadCrumbs.shouldBe(visible);
            aptekaCataloguePage.breadCrumbsMain.shouldHave(text("Главная"));
            aptekaCataloguePage.breadCrumbsCatalogue.shouldHave(text("Каталог"));
            aptekaCataloguePage.breadCrumbsSubCatalogue.shouldHave(text("Косметика"));
            aptekaCataloguePage.breadCrumbsCategory.shouldHave(text(Variables.searchVariables));
        });

        step("Проверить, что в списке продуктов отображается хотя бы 1 товар категории", () -> {
            assertTrue(aptekaCataloguePage.productsCollection.size() > 1);
        });
    }

    @Test
    @DisplayName("Поиск товара")
    public void shouldSearchingResults() {
        step("Ввести в строку поиска слово или словосочетание и нажать enter", () -> {
            aptekaMainPage.searchField.sendKeys(Variables.searchOneWordValidTextFirst);
            aptekaMainPage.searchField.pressEnter();
        });

        step("Убедиться, что переход на страницу результатов успешен", () -> {
            aptekaSearchPage.pagetitle.shouldBe(visible);
            aptekaSearchPage.pagetitle.shouldHave(text("Поиск"));
        });

        step("Проверить, что результат поиска не пустой", () -> {
            assertFalse(aptekaSearchPage.productsCollection.isEmpty());
        });

        step("Проверить, что первый результат поиска содержит именно то, что искали", () -> {
            aptekaSearchPage.productsCollection.get(0).shouldHave(text(Variables.searchOneWordValidTextFirst));
        });

        step("Проверить, что все результаты поиска содержат именно то, что искали", () -> {
            aptekaSearchPage.listProduct();
        });

        step("Проверить, что в поисковой выдаче 5 результатов", () -> {
            assertEquals(5, aptekaSearchPage.productsCollection.size());
        });
    }

    @Test
    @DisplayName("Кнопка Отложить")
    public void shouldWishItemResults() {
        step("Ввести в строку поиска запрос и нажать enter", () -> {
            aptekaMainPage.searchField.sendKeys(Variables.searchOneWordValidTextSecond);
            aptekaMainPage.searchField.pressEnter();
        });

        step("Навести курсор на товар из результатов поиска", () -> {
            aptekaSearchPage.likeIcons.hover();
        });

        step("Проверить, что иконка с кнопкой Отложить отображается", () -> {
            aptekaSearchPage.wishItemButton.shouldBe(visible);
            aptekaSearchPage.wishItemButton.shouldHave(Condition.attribute("title", "Отложить"));
        });

        step("Нажать на иконку с кнопкой Отложить", () -> {
            aptekaSearchPage.wishItemButton.click();
        });

        step("Проверить, что иконка изменила название на В отложенных и отображается", () -> {
            aptekaSearchPage.wishItemButton.shouldBe(Condition.not(visible));
            aptekaSearchPage.wishItemButtonOnPending.shouldBe(visible);
            aptekaSearchPage.wishItemButtonOnPending.shouldHave(Condition.attribute("title", "В отложенных"));
        });

        step("Нажать на иконку с отложенными товарами", () -> {
            aptekaSearchPage.iconHeart.click();
        });

        step("Проверить, что переход успешен и отображается целевой раздел", () -> {
            aptekaBasketPage.pagetitle.shouldBe(visible);
        });

        step("Проверить, что выбранный товар отображается в отложенных", () -> {
            aptekaBasketPage.productValueInDelayed.shouldBe(visible);
        });

        step("Проверить, что товар отложен", () -> {
            aptekaBasketPage.itemDelayed.shouldHave(Condition.text("Товар отложен"));
        });

        step("Проверить, что цена отложенного товара не учитывается в итоговой сумме заказа", () -> {
            aptekaBasketPage.checkPriceBasketAndDelayed();
        });
    }
}
