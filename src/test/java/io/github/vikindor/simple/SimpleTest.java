package io.github.vikindor.simple;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Story("Simple test")
public class SimpleTest {

    @Test
    public void shouldFindIssueNumberTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".search-input-container").click();
        $("#query-builder-test").val("selenide/selenide").submit();

        // Instead of Selenium's $(linkText("selenide/selenide")).click();
        $$("a").findBy(text("selenide/selenide")).click();

        $("#issues-tab").click();
        $$("[data-testid=list-row-repo-name-and-number]").findBy(text("2757")).shouldBe(visible);
    }
}
