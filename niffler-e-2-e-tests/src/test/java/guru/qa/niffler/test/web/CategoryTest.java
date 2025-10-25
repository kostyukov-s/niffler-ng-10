package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.jupiter.extension.SpendingExtension;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpendingExtension.class)
public class CategoryTest {
    private static final Config CFG = Config.getInstance();

    @Category(
            username = "duck",
            archived = false
    )
    @Test
    public void archiveCategoryTest(CategoryJson categoryJson) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login(categoryJson.username(), "12345")
                .editProfile()
                .archiveCategory(categoryJson.name())
                .checkShowArchivedCategories()
                .isCategoryExists(categoryJson.name());
    }

    @Category(
            username = "duck",
            archived = true
    )
    @Test
    public void unArchiveCathegoryTest(CategoryJson categoryJson) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login(categoryJson.username(), "12345")
                .editProfile()
                .checkShowArchivedCategories()
                .unArchiveCategory(categoryJson.name())
                .uncheckShowArchivedCategories()
                .isCategoryExists(categoryJson.name());
    }
}
