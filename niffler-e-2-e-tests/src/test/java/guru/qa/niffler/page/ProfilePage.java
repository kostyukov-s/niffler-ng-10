package guru.qa.niffler.page;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    private final SelenideElement uploadPictureButton = $(".image__input-label");
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement nameInput = $("#name");
    private final SelenideElement saveChangesButton = $("#:r12:");
    private final SelenideElement registerPassKeyButton = $("#:r11:");
    private final SelenideElement addNewCategoryInput = $("#category");
    private final SelenideElement showArchivedCategoriesCheckbox = $x("//span[contains(text(),'Show archived')]/..//input[@type='checkbox']");
    private final SelenideElement submitArchive = $x("//button[contains(@class,'MuiButtonBase-root') and normalize-space(text())='Archive']");
    private final SelenideElement submitUnArchive = $x("//button[contains(@class,'MuiButtonBase-root') and normalize-space(text())='Unarchive']");

    private SelenideElement findCategory(String category) {
        return $x("//span[contains(@class,'MuiChip-label') and text()='" + category + "']/ancestor::*[2]");
    }

    private SelenideElement editCategoryButton(String category) {
        return findCategory(category).find("[aria-label=\"Edit category\"]");
    }

    private SelenideElement archiveCategoryButton(String category) {
        return findCategory(category).find("[aria-label=\"Archive category\"]");
    }

    private SelenideElement unArchiveCategoryButton(String category) {
        return findCategory(category).find("[aria-label=\"Unarchive category\"]");
    }

    public ProfilePage uploadPicture(File imageFile) {
        uploadPictureButton.uploadFile(imageFile);
        return this;
    }

    public ProfilePage clickRegisterPassKey() {
        registerPassKeyButton.click();
        return this;
    }

    public ProfilePage checkUserNameInputValue(String value) {
        usernameInput.shouldHave(text(value));
        return this;
    }

    public ProfilePage isUsernameFieldVisible() {
        usernameInput.shouldBe(visible);
        return this;
    }

    public ProfilePage setName(String name) {
        nameInput.val(name);
        return this;
    }

    public ProfilePage clearName() {
        nameInput.clear();
        return this;
    }

    public ProfilePage checkNameInputValue(String value) {
        nameInput.shouldHave(text(value));
        return this;
    }

    public ProfilePage isNameFieldVisible() {
        nameInput.shouldBe(visible);
        return this;
    }

    public ProfilePage clickSaveChanges() {
        saveChangesButton.click();
        return this;
    }

    public ProfilePage checkShowArchivedCategories() {
        if (!showArchivedCategoriesCheckbox.isSelected()) {
            showArchivedCategoriesCheckbox.click(ClickOptions.usingJavaScript());
        }
        return this;
    }

    public ProfilePage uncheckShowArchivedCategories() {
        if (showArchivedCategoriesCheckbox.isSelected()) {
            showArchivedCategoriesCheckbox.click(ClickOptions.usingJavaScript());
        }
        return this;
    }

    public ProfilePage toggleShowArchivedCategories() {
        showArchivedCategoriesCheckbox.shouldBe(clickable).click();
        return this;
    }

    public ProfilePage isShowArchivedCategoriesChecked() {
        showArchivedCategoriesCheckbox.shouldBe(selected);
        return this;
    }

    public ProfilePage setNewCategoryName(String categoryName) {
        addNewCategoryInput.val(categoryName);
        return this;
    }

    public ProfilePage addNewCategory(String categoryName) {
        setNewCategoryName(categoryName).pressEnterOnNewCategory();
        return this;
    }

    public ProfilePage clearNewCategoryInput() {
        addNewCategoryInput.clear();
        return this;
    }

    public ProfilePage pressEnterOnNewCategory() {
        addNewCategoryInput.pressEnter();
        return this;
    }

    public ProfilePage checkNewCategoryInputValue(String value) {
        addNewCategoryInput.shouldHave(text(value));
        return this;
    }

    public ProfilePage clickEditCategoryButton(String category) {
        editCategoryButton(category);
        return this;
    }

    public ProfilePage archiveCategory(String category) {
        archiveCategoryButton(category).click();
        submitArchive.click();
        return this;
    }

    public ProfilePage unArchiveCategory(String category) {
        unArchiveCategoryButton(category).click();
        submitUnArchive.click();
        return this;
    }

    public ProfilePage isCategoryExists(String category) {
        findCategory(category).shouldBe(exist);
        return this;
    }
}
