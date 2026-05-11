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
    private final String folderName = "vvss_test_folder";
    private final String uploadedFileName = "uploadTestFile.txt";
    private final String uploadFilePath = "src/test/resources/uploadTestFile.txt";

    @Test
    public void user_can_create_upload_and_delete_items() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        loginPage.accept_cookies_if_present();

        loginPage.login_steps(server, user, pass);
        accountPage.should_be_in_user_directory("/home/" + user);

        accountPage.create_directory_with_name(folderName);
        accountPage.should_be_able_to_see_new_directory(folderName);

        accountPage.upload_file(uploadFilePath);
        accountPage.should_see_uploaded_file(uploadedFileName);

        accountPage.select_file_to_delete(uploadedFileName);
        accountPage.delete_selected_file();
        accountPage.confirm_delete();
        accountPage.should_not_see_uploaded_file(uploadedFileName);

        accountPage.select_directory_to_delete(folderName);
        accountPage.delete_selected_directory();
        accountPage.confirm_delete();
        accountPage.should_not_be_able_to_see_new_directory(folderName);

        accountPage.logout();
        logoutPage.should_see_logout_message("You have logged out from the FTP server.");
    }
}
