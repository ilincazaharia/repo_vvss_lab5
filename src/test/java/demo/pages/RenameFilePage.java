package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class RenameFilePage extends PageObject {

    @FindBy(xpath="//*[@id=\"RenameForm\"]/a[2]")
    private WebElementFacade saveButton;

    @FindBy(xpath="//*[@id=\"RenameForm\"]/a[1]")
    private WebElementFacade backButtonBtn;

    @FindBy(xpath = "//*[@id=\"RenameForm\"]/input[31]")
    private WebElementFacade newFileName;

    public void enter_new_file_name(String filename) {
        newFileName.type(filename);
    }

    public void click_to_rename_File() {
        saveButton.click();
    }

    public void back() {
        backButtonBtn.click();
    }
}
