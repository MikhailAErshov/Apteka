package com.example.apteka;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AptekaSearchPage {
    public SelenideElement pagetitle = $x("//*[@id=\"pagetitle\"]");
    public ElementsCollection productsCollection = $$x("//div[@class='item-title']/a[@href]/span");
    public SelenideElement likeIcons = $x("//div[@class='like_icons']");
    public SelenideElement wishItemButton = $x("//div[@class='wish_item_button']/span[1]");
    public SelenideElement wishItemButtonOnPending = $x("//div[@class='wish_item_button']/span[2]");
    public SelenideElement iconHeart = $x("//a[@class='basket-link delay with_price big  basket-count']");
    public SelenideElement priceValue = $x("//div[@id='bx_3966226736_116470']//span[@class='price_value']");

    public void listProduct() {
        List<String> searchProductResult = new ArrayList<>();
        productsCollection.forEach(product -> searchProductResult.add(product.getText()));

        for (int i = 0; i < searchProductResult.size(); i++) {
            String everyResult = searchProductResult.get(i);
            assertTrue(everyResult.contains(Variables.searchOneWordValidTextFirst));
        }
    }

    public void checkPriceComparison() {
        String price = iconHeart.getAttribute("title");
        priceValue.shouldHave(Condition.text(price.replaceFirst("\\D*(\\d*).*", "$1")));
    }
}
