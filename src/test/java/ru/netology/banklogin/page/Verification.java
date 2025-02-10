package ru.netology.banklogin.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.banklogin.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Verification {
    private final SelenideElement codeField = $("[data-test-id=code] input");

    public Verification() {
        codeField.shouldBe(visible);
    }

    public void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        $("[data-test-id=action-verify]").click();
    }

    public Dashboard validVerify(String verificationCode) {
        verify(verificationCode);
        return new Dashboard();
    }

    public void verifyErrorNotification(String expectedText) {
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(exactText(expectedText))
                .shouldBe(visible);
    }
}