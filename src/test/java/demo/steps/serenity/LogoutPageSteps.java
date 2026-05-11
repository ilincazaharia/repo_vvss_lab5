package demo.steps.serenity;

import demo.pages.LogoutPage;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class LogoutPageSteps {

    LogoutPage logoutPage;

    @Step
    public void should_see_logout_message(String message) {
        assertThat(logoutPage.getPageText(), containsString(message));
    }
}
