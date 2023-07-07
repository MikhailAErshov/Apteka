package com.example.apteka;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AptekaCataloguePage {
    public SelenideElement pagetitle = $x("//*[@id='pagetitle']");
    public SelenideElement breadCrumbs = $x("//*[@id='navigation']");
    public SelenideElement breadCrumbsMain = $x("//*[@id='bx_breadcrumb_0']/a/span");
    public SelenideElement breadCrumbsCatalogue = $x("//*[@id='bx_breadcrumb_1']/a/span");
    public SelenideElement breadCrumbsSubCatalogue = $x("//*[@id='bx_breadcrumb_2']/a/span");
    public SelenideElement breadCrumbsCategory = $x("//span[@class=' bx-breadcrumb-item--mobile']/span/span");
    public ElementsCollection productsCollection = $$x("//div[@class='item-title']/a[@href]/span");
    public SelenideElement breadCrumbCollection = $x("//*[@id='navigation']");

    public void checkMovedToCategory(SelenideElement title, String subcategory) {
        title.should(visible, Duration.ofSeconds(3));
        title.shouldHave(text(subcategory));
    }

    public void checkBreadCrumbs(String[] breadcrumbs) {
        breadCrumbs.shouldBe(visible);
        String breadCrumbResult = breadCrumbCollection.getText();
        String[] breadCrumbResultArray = breadCrumbResult.split("-");

        assertArrayEquals(breadcrumbs, breadCrumbResultArray);
    }
}
