package com.example.apteka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AptekaMainPage {
    public SelenideElement confirmRegion = $x("//div[@class='confirm_region']");
    public SelenideElement tabCatalogue = $x("//a/div[contains(text(), 'Косметика')]");
    public SelenideElement tabCataloguePoint = $x("//span[contains(text(),'Гели для душа')]");
    public SelenideElement searchField = $x("//*[@id='title-search-input_fixed']");

    public void checkTheTransitionToACategoryWithAProduct() {
        tabCatalogue.hover();
        tabCataloguePoint.click();
    }
}
