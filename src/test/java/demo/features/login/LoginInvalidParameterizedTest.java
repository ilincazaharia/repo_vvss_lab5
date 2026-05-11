package demo.features.login;

import demo.steps.serenity.LoginPageSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/invalidData.csv")
public class LoginInvalidParameterizedTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps loginPage;

    String server, user, pass, message;

    @Test
    public void invalid_login_should_show_error_message() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        loginPage.accept_cookies_if_present();
        loginPage.login_steps(server, user, pass);
        loginPage.should_see_error_message(message);
    }
}
