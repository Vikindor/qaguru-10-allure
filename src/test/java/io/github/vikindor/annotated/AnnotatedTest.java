package io.github.vikindor.annotated;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

@Story("Simple test with annotations")
public class AnnotatedTest {

    private final String repository = "selenide/selenide";
    private final String tabName = "issues";
    private final String issueNumber = "2757";

    @Test
    public void shouldFindIssueNumberTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps step = new WebSteps();

        step.openGitHub();
        step.searchForRepository(repository);
        step.clickOnRepository(repository);
        step.openTab(tabName);
        step.findIssueByNumber(issueNumber);
    }
}
