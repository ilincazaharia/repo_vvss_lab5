package demo.steps.serenity;

import demo.pages.NewFilePage;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class NewFilePageSteps {
    NewFilePage newFilePage;

    @Step
    public void enter_file_content(String content) {
        newFilePage.enter_file_content(content);
    }

    @Step
    public void enter_filename(String filename) {
        newFilePage.enter_file_name(filename);
    }

    @Step
    public void createFile(String filename) {
        enter_filename(filename);
        should_be_able_to_see_message("Status: This file has not yet been saved");
        newFilePage.click_to_create_File();
    }

    @Step
    public void createFile(String content,String filename) {
        enter_file_content(content);
        createFile(filename);
    }

    @Step
    public void click_edit_file(String content, String filename) {
        enter_file_content(content);
        should_be_able_to_see_message("Status: This file has not yet been saved");
        newFilePage.click_to_create_File();
        back();
    }

    @Step
    public void back() {
        newFilePage.back();
    }

    @Step
    public void should_be_able_to_see_message(String message) {
        assert(newFilePage.getStatusMessage().contains(message));
    }
}