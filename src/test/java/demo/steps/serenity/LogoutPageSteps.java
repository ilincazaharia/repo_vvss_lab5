package demo.steps.serenity;

import net.thucydides.core.annotations.Step;
import demo.pages.LogoutPage;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class LogoutPageSteps {

    LogoutPage logoutPage;

    @Step
    public void should_see_logout_message(String message) {
        assertThat(logoutPage.getDefinitions(), hasItem(containsString(message)));
    }

}
