package io.github.vikindor.lambda;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Story("Simple test with lambdas")
public class LambdaTest {

    private final String mainPage = "https://github.com";
    private final String repository = "selenide/selenide";
    private final String issueNumber = "2757";

    @Test
    public void shouldFindIssueNumberTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open GitHub's main page", () -> {
            open(mainPage);
        });
        step("Click the search field", () -> {
            $(".search-input-container").click();
        });
        step("Enter search query", () -> {
            $("#query-builder-test").val(repository).submit();
        });
        step("Find and click the 'selenide' repository link", () -> {
            $$("a").findBy(text(repository)).click();
        });
        step("Click the 'Issues' tab", () -> {
            $("#issues-tab").click();
        });
        step("Find issue #2757", () -> {
            $$("[data-testid=list-row-repo-name-and-number]").findBy(text(issueNumber)).shouldBe(visible);
        });
    }
}
