package ru.netology.banklogin.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Dashboard {
    private final SelenideElement header = $("[data-test-id=dashboard]");

    public Dashboard() {
        header.shouldHave(text("Личный кабинет")).shouldBe(visible);
    }

}