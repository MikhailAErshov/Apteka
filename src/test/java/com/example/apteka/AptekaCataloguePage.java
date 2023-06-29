package com.example.apteka;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class AptekaCataloguePage {
    public SelenideElement pagetitle = $x("//*[@id=\"pagetitle\"]");
    public SelenideElement breadCrumbs = $x("//*[@id=\"navigation\"]");
    public SelenideElement breadCrumbsMain = $x("//*[@id=\"bx_breadcrumb_0\"]/a/span");
    public SelenideElement breadCrumbsCatalogue = $x("//*[@id=\"bx_breadcrumb_1\"]/a/span");
    public SelenideElement breadCrumbsSubCatalogue = $x("//*[@id=\"bx_breadcrumb_2\"]/a/span");
    public SelenideElement breadCrumbsCategory = $x("//span[@class=' bx-breadcrumb-item--mobile']/span/span");
    public ElementsCollection productsCollection = $$x("//div[@class='item-title']/a[@href]/span");
}
