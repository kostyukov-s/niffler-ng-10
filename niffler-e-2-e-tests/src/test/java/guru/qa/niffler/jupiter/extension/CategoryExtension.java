package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.service.SpendApiClient;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.util.Random;

public class CategoryExtension implements BeforeEachCallback, ParameterResolver, AfterTestExecutionCallback {

    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CategoryExtension.class);
    private final SpendApiClient spendClient = new SpendApiClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AnnotationSupport.findAnnotation(
                context.getRequiredTestMethod(),
                Category.class
        ).ifPresent(annotation -> {
            CategoryJson created = spendClient.createCategory(
                    new CategoryJson(
                            null,
                            annotation.username() + new Random().nextInt(1000, 10000),
                            annotation.username(),
                            annotation.archived()
                    )
            );
            if (annotation.archived()) {
                CategoryJson archivedCategory = new CategoryJson(
                        created.id(),
                        created.name(),
                        created.username(),
                        true
                );
                created = spendClient.updateCategory(archivedCategory);
            }
            context.getStore(NAMESPACE).put(
                    context.getUniqueId(),
                    created
            );
        });
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        try {
            CategoryJson category = context.getStore(NAMESPACE)
                    .get(context.getUniqueId(), CategoryJson.class);
            if (!category.archived()) {
                CategoryJson archivedCategory = new CategoryJson(
                        category.id(),
                        category.name(),
                        category.username(),
                        true
                );
                spendClient.updateCategory(archivedCategory);
            }
        } catch (Exception e) {
            System.err.println("Exception during category cleanup: " + e.getMessage());
        }

    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(CategoryJson.class);
    }

    @Override
    public CategoryJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), CategoryJson.class);
    }
}
