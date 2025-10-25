package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.meta.WebTest;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

import java.util.Random;

@WebTest
public class RegistrationTest {

    private static final Config CFG = Config.getInstance();
    private final String username = "testUser" + new Random().nextInt();
    private final String password = "TeStPwD";

    @Test
    void shouldRegisterNewUser() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .registerNewUser()
                .checkElements()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(password)
                .register()
                .signIn()
                .checkElements()
                .login(username, password)
                .checkElements();
    }

    @Test
    void shouldNotRegisterUserWithExistingUsername() {
        final String username = "duck";
        final String password = "12345";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .registerNewUser()
                .checkElements()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(password)
                .register()
                .checkFormError(String.format("Username `%s` already exists", username));
    }

    @Test
    void shouldShowErrorIfPasswordAndConfirmPasswordAreNotEqual() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .registerNewUser()
                .checkElements()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit("badPassword")
                .register()
                .checkFormError("Passwords should be equal");
    }
}