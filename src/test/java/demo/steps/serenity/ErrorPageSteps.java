package demo.steps.serenity;

import demo.pages.ErrorPage;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ErrorPageSteps {

    ErrorPage errorPage;

    @Step
    public void should_see_message(String message) {
        assertThat(errorPage.getPageText(), containsString(message));
    }
}