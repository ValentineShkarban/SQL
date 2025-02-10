package ru.netology.banklogin.page;

import com.codeborne.selenide.Condition;
import ru.netology.banklogin.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Login {

    public void login(DataHelper.UserInfo info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
    }

    public Verification validLogin(DataHelper.UserInfo info) {
        login(info);
        return new Verification();
    }

    public void verifyErrorNotification(String expectedText) {
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(exactText(expectedText))
                .shouldBe(visible);
    }
}