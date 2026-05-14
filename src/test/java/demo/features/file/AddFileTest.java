package demo.features.file;

import demo.steps.serenity.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class AddFileTest {

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

    @Steps
    public ViewFilePageSteps viewFilePage;

    @Steps
    public RenameFileSteps renameFilePage;

    private final String server = "localhost";
    private final String user = "vvta3";
    private final String pass = "vvta3";
    private final String filename = "testfile";
    private final String content = "This is a test file";
    private final String newContent = "This is an edited test file";
    private final String newFileName = "renamedfile";

    @Test
    public void user_can_login_create_edit_delete_file_and_logout() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        loginPage.accept_cookies_if_present();

        loginPage.login_steps(server, user, pass);
        userLoggedIn.should_be_in_user_directory("/home/" + user);
        //create file
        userLoggedIn.newFile();
        newFilePage.createFile(content, filename);
        newFilePage.back();
        userLoggedIn.should_be_able_to_see_file(filename);
        //rename file
        userLoggedIn.select_file(filename);
        userLoggedIn.renameFile();
        renameFilePage.renameFile(newFileName);
        userLoggedIn.should_be_able_to_see_file(newFileName);
        userLoggedIn.should_not_be_able_to_see_new_file(filename);
        //edit file
        userLoggedIn.editFile(newFileName);
        newFilePage.click_edit_file(newContent,newFileName);
        //view file to check that its edited
        userLoggedIn.viewFile(newFileName);
        viewFilePage.should_be_able_to_see_content(newContent);
        viewFilePage.back();
        //delete file
        userLoggedIn.select_file(newFileName);
        userLoggedIn.delete_selected_file();
        userLoggedIn.should_not_be_able_to_see_new_file(newFileName);
        userLoggedIn.logout();
        logoutPage.should_see_logout_message("You have logged out from the FTP server.");
    }
}
