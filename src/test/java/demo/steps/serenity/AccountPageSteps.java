package demo.steps.serenity;

import demo.pages.AccountPage;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class AccountPageSteps {

    AccountPage accountPage;

    @Step
    public void should_be_in_user_directory(String userDirectory) {
        Assert.assertTrue(
                "Login valid failed. Expected to be in net2ftp account page for " + userDirectory
                        + ", but page was: " + accountPage.getBodyText(),
                accountPage.isUserLoggedIn()
        );
    }

    @Step
    public void logout() {
        accountPage.click_Logout();
    }

    @Step
    public void newDirectory() {
        accountPage.click_new_directory();
    }

    @Step
    public void create_directory_with_name(String directoryName) {
        accountPage.click_new_directory();
        accountPage.enter_new_directory_name(directoryName);
        accountPage.submit_new_directory();
        accountPage.go_back_to_file_list();
    }

    @Step
    public void should_be_able_to_see_new_directory(String createdDirectory) {
        Assert.assertTrue(
                "Folderul nu apare in pagina: " + createdDirectory,
                accountPage.pageContainsText(createdDirectory)
        );
    }

    @Step
    public void should_not_be_able_to_see_new_directory(String createdDirectory) {
        Assert.assertFalse(
                "Folderul inca apare in pagina: " + createdDirectory,
                accountPage.pageContainsText(createdDirectory)
        );
    }

    @Step
    public void upload_file(String filePath) {
        accountPage.upload_file(filePath);
    }

    @Step
    public void should_see_uploaded_file(String fileName) {
        Assert.assertTrue(
                "Fisierul uploadat nu apare in pagina: " + fileName,
                accountPage.pageContainsText(fileName)
        );
    }

    @Step
    public void should_not_see_uploaded_file(String fileName) {
        Assert.assertFalse(
                "Fisierul inca apare in pagina: " + fileName,
                accountPage.pageContainsText(fileName)
        );
    }

    @Step
    public void select_directory_to_delete(String directory) {
        accountPage.check_item_to_delete(directory);
    }

    @Step
    public void select_file_to_delete(String fileName) {
        accountPage.check_item_to_delete(fileName);
    }

    @Step
    public void delete_selected_directory() {
        accountPage.delete_selected_item();
    }

    @Step
    public void delete_selected_file() {
        accountPage.delete_selected_item();
    }

    @Step
    public void confirm_delete() {
        accountPage.confirm_delete_directory();
    }
}