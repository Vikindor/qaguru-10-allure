package io.github.vikindor.annotated;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {

    @Step("Open GitHub's main page")
    public void openGitHub() {
        open("https://github.com");
    }

    @Step("Search for repository {repository}")
    public void searchForRepository(String repository) {
        $(".search-input-container").click();
        $("#query-builder-test").val(repository).submit();
    }

    @Step("Find and click the {repository} repository link")
    public void clickOnRepository(String repository) {
        $$("a").findBy(text(repository)).click();
    }

    @Step("Click the {tabName} tab")
    public void openTab(String tabName) {
        $("#" + tabName + "-tab").click();
    }

    @Step("Find issue #{issueNumber}")
    public void findIssueByNumber(String issueNumber) {
        $$("[data-testid=list-row-repo-name-and-number]").findBy(text(issueNumber)).shouldBe(visible);
    }
}
