package demo.features.file;

import demo.steps.serenity.AccountPageSteps;
import demo.steps.serenity.LoginPageSteps;
import demo.steps.serenity.LogoutPageSteps;
import demo.steps.serenity.NewFilePageSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src\\test\\resources\\fileValidData.csv")
public class AddFileValidDataTest {
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

    public String server="localhost",user="vvta23",pass="vvta23",content, filename, message;
    private Boolean is_visible;

    @Test
    public void addInvalidFileTest()
    {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        loginPage.accept_cookies_if_present();

        loginPage.login_steps(server, user, pass);
        userLoggedIn.newFile();
        newFilePage.createFile(content, filename);
        newFilePage.back();
        userLoggedIn.should_be_able_to_see_file(filename);
        userLoggedIn.select_file(filename);
        userLoggedIn.delete_selected_file();
        userLoggedIn.logout();
    }
}
