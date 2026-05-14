package demo.steps.serenity;

import demo.pages.RenameFilePage;
import net.thucydides.core.annotations.Step;

public class RenameFileSteps {
    RenameFilePage renameFilePage;

    @Step
    public void enter_filename(String filename) {
        renameFilePage.enter_new_file_name(filename);
    }

    @Step
    public void renameFile(String filename) {
        enter_filename(filename);
        renameFilePage.click_to_rename_File();
        back();
    }

    @Step
    public void renameFile() {
        renameFilePage.click_to_rename_File();
    }

    @Step
    public void back() {
        renameFilePage.back();
    }
}
