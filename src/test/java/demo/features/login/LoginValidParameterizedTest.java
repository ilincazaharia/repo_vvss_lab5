package demo.features.login;

import demo.steps.serenity.AccountPageSteps;
import demo.steps.serenity.LoginPageSteps;
import demo.steps.serenity.LogoutPageSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/validData.csv")
public class LoginValidParameterizedTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps loginPage;

    @Steps
    public AccountPageSteps accountPage;

    @Steps
    public LogoutPageSteps logoutPage;

    String server, user, pass, message;

    @Test
    public void valid_login_should_open_user_directory() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        loginPage.accept_cookies_if_present();
        loginPage.login_steps(server, user, pass);
        accountPage.should_be_in_user_directory("/home/" + user);

        accountPage.logout();
        logoutPage.should_see_logout_message(message);
    }
}
