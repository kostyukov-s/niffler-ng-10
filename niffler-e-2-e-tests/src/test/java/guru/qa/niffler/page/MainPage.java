package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

  private final SelenideElement statistics = $("#stat");
  private final SelenideElement spendingTable = $("#spendings");
  private final SelenideElement menuButton = $("button[aria-label='Menu']");
  private final SelenideElement menu = $("ul[role='menu']");
  public final ElementsCollection menuItems = menu.$$("li");

  public MainPage checkThatPageLoaded() {
    spendingTable.should(visible);
    return this;
  }

  public EditSpendingPage editSpending(String description) {
    spendingTable.$$("tbody tr").find(text(description)).$$("td").get(5).click();
    return new EditSpendingPage();
  }

  public MainPage checkThatTableContains(String description) {
    spendingTable.$$("tbody tr").find(text(description)).should(visible);
    return this;
  }

  public ProfilePage editProfile() {
    menuButton.click();
    menuItems.find(text("Profile")).click();
    return new ProfilePage();
  }

  public MainPage checkElements() {
    statistics.should(visible);
    spendingTable.shouldBe(visible);
    return this;
  }
}
