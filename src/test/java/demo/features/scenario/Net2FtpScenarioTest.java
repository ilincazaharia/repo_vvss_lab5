package demo.features.scenario;

import demo.steps.serenity.AccountPageSteps;
import demo.steps.serenity.LoginPageSteps;
import demo.steps.serenity.LogoutPageSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class Net2FtpScenarioTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps loginPage;

    @Steps
    public AccountPageSteps accountPage;

    @Steps
    public LogoutPageSteps logoutPage;

    private final String server = "localhost";
    private final String user = "vvta3";
    private final String pass = "vvta3";
    private final String folderName = "vvss_test_folder_" + System.currentTimeMillis();

    @Test
    public void user_can_login_create_directory_and_logout() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        loginPage.accept_cookies_if_present();

        loginPage.login_steps(server, user, pass);
        accountPage.should_be_in_user_directory("/home/" + user);

        accountPage.create_directory_with_name(folderName);
        accountPage.should_be_able_to_see_new_directory(folderName);

        accountPage.logout();
        logoutPage.should_see_logout_message("You have logged out from the FTP server.");
    }
}