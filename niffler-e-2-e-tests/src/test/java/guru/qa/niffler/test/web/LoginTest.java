package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.meta.WebTest;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

@WebTest
public class LoginTest {

  private static final Config CFG = Config.getInstance();
  private final String username = "testUser" + new Random().nextInt();
  private final String password = "TeStPwD";

  @Test
  void mainPageShouldBeDisplayedAfterSuccessLogin() {
    Selenide.open(CFG.frontUrl(), LoginPage.class)
        .login("duck", "12345")
        .checkThatPageLoaded();
  }

  @Test
  void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {
    Selenide.open(CFG.frontUrl(), LoginPage.class)
            .setUsername(username)
            .setPassword("WrongPassword")
            .submit()
            .checkFormError("Неверные учетные данные пользователя")
            .checkElements();
  }
}
