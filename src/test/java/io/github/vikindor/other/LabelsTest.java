package io.github.vikindor.other;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {

    @Test
    @Epic("GitHub")
    @Feature("Issues")
    @Story("Issue search")
    @Owner("vikindor")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Some test name")
    public void testStaticLabels() {
    }

    @Test
    public void testDynamicLabels() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Some test name")
        );
        Allure.epic("GitHub");
        Allure.feature("Issues");
        Allure.story("Issue search");
        Allure.label("owner", "vikindor");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("Testing", "https://testing.github.com");
    }

}