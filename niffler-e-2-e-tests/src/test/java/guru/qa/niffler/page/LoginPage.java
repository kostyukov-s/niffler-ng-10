package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement submitButton = $("#login-button");
    private final SelenideElement registerButton = $("#register-button");
    private final SelenideElement formError = $(".form__error");

    public MainPage login(String username, String password) {
        usernameInput.val(username);
        passwordInput.val(password);
        submitButton.click();
        return new MainPage();
    }

    public LoginPage setUsername(String username) {
        usernameInput.val(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.val(password);
        return this;
    }

    public LoginPage submit() {
        submitButton.click();
        return this;
    }

    public RegisterPage registerNewUser() {
        registerButton.click();
        return new RegisterPage();
    }

    public LoginPage checkElements() {
        usernameInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        submitButton.shouldBe(visible);
        registerButton.shouldBe(visible);
        return this;
    }

    public LoginPage checkFormError(String errorText) {
        formError.shouldHave(ownText(errorText));
        return this;
    }
}
