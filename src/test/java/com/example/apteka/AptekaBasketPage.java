package com.example.apteka;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AptekaBasketPage {
    AptekaSearchPage aptekaSearchPage = new AptekaSearchPage();
    public SelenideElement pagetitle = $x("//*[@id=\"pagetitle\"]");
    public SelenideElement itemDelayed = $x("//div[@class='alert alert-warning text-center']");
    public SelenideElement delayedTotalPrice = $x("//div[@data-entity='basket-total-price']");
    public SelenideElement productPriceInDelayed = $x("//div[@class='basket-item-block-price']//span");
    public SelenideElement productValueInDelayed = $x("//a[@class='basket-item-info-name-link']/span");

    public void checkPriceBasketAndDelayed() {
        delayedTotalPrice.shouldNotHave(Condition.text(productPriceInDelayed.getText()));
    }
}
