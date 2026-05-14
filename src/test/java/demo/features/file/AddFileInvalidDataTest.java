package demo.features.file;

import demo.steps.serenity.*;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertThat;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src\\test\\resources\\fileInvalidData.csv")
public class AddFileInvalidDataTest {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps loginPage;

    @Steps
    public LogoutPageSteps logoutPage;

    @Steps
    public AccountPageSteps userLoggedIn;

    @Steps
    public NewFilePageSteps newFilePage;

    @Steps ErrorPageSteps errorPage;

    public String server="localhost",user="vvta23",pass="vvta23",content, filename, message;

    @Test
    public void addInvalidFileTest()
    {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        loginPage.accept_cookies_if_present();

        loginPage.login_steps(server, user, pass);
        userLoggedIn.newFile();
        newFilePage.createFile(content, filename);
        errorPage.should_see_message(message);
    }
}
