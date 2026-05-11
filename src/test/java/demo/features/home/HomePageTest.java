package demo.features.home;

import demo.steps.serenity.LoginPageSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class HomePageTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps loginPage;

    @Test
    public void login_page_should_display_basic_ftp_login_form() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        loginPage.accept_cookies_if_present();

        loginPage.should_see_text("Basic FTP login");
        loginPage.should_see_text("FTP server");
        loginPage.should_see_text("Username");
        loginPage.should_see_text("Password");
    }

    @Test
    public void login_page_should_display_available_features() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        loginPage.accept_cookies_if_present();

        loginPage.should_see_text("Features");
        loginPage.should_see_text("Navigate the FTP server");
        loginPage.should_see_text("Upload and download files");
        loginPage.should_see_text("Edit files");
    }
    @Test
    public void login_page_should_display_language_options() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        loginPage.accept_cookies_if_present();

        loginPage.should_see_text("English");
        loginPage.should_see_text("French");
        loginPage.should_see_text("German");
        loginPage.should_see_text("Spanish");
    }
}