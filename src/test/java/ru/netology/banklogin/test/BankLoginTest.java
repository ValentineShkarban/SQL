package ru.netology.banklogin.test;

import org.junit.jupiter.api.*;
import ru.netology.banklogin.data.DataHelper;
import ru.netology.banklogin.data.SQLHelper;
import ru.netology.banklogin.page.Login;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.banklogin.data.SQLHelper.cleanAuthCodes;
import static ru.netology.banklogin.data.SQLHelper.cleanDatabase;

public class BankLoginTest {
    Login login;
    DataHelper.UserInfo userInfo = DataHelper.getAuthInfoWithTestData();

    @AfterAll
    static void clearAllData() {
        cleanDatabase();
    }

    @AfterEach
    void clearAllCodes() {
        cleanAuthCodes();
    }

    @BeforeEach
    void setUp() {
        login = open("http://localhost:9999", Login.class);
    }

    @Test
    @DisplayName("Should successfully login to dashboard with existing login and password from sut test data")
    void loginSuccessfully() {
        var login = new Login();
        var verification = login.validLogin(userInfo);
        verification.validVerify(SQLHelper.getVerificationCode());
    }

    @Test
    @DisplayName("Should get error notification if no such user in database")
    void errorIfUserDoesNotExist() {
        var login = new Login();
        var randomUser = DataHelper.generateRandomUser();
        login.login(randomUser);
        login.verifyErrorNotification("Ошибка! \nНеверно указан логин или пароль");
    }

    @Test
    @DisplayName("Should get error notification if user exists in database but verification code is random")
    void errorWithRandomVerificationCode() {
        var verification = login.validLogin(userInfo);
        var randomVerificationCode = DataHelper.generateRandomVerificationCode();
        verification.verify(randomVerificationCode.getCode());
        verification.verifyErrorNotification("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }
}