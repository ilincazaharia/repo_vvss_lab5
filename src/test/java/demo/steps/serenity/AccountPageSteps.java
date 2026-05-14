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

        //assertThat("current directory", userDirectory.equals(userLoggedIn.getCurrentDirectoryName()));
        //Assert.assertTrue(userLoggedIn.getCurrentDirectoryName(), userDirectory.equals(userLoggedIn.getCurrentDirectoryName()));
        Assert.assertTrue(userDirectory.contains(accountPage.getCurrentDirectoryName()));
    }

    @Step
    public void logout() {

        accountPage.click_Logout();
    }

    @Step
    public void newFile() {
        accountPage.click_new_file();
    }

    @Step
    public void editFile(String fileName)
    {
        accountPage.click_edit_file(fileName);
    }

    @Step
    public void viewFile(String fileName)
    {
        accountPage.click_view_file(fileName);
    }

    @Step
    public void renameFile()
    {
        accountPage.click_rename_file();
    }

    @Step
    public void should_be_able_to_see_file(String fileName) {
        assertThat(accountPage.getContent(), hasItem(containsString(fileName)));
    }

    @Step
    public void should_not_be_able_to_see_new_file(String fileName) {
        assertThat(accountPage.getContent(), not(hasItem(containsString(fileName))));
    }

    @Step
    public void select_file(String fileName) {

        accountPage.select_file(fileName);
    }

    @Step
    public void delete_selected_file() {
        accountPage.deleteFile();
    }
}