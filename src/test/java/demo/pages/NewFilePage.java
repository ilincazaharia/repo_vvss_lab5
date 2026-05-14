package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class NewFilePage extends PageObject {

    @FindBy(name="entry")
    private WebElementFacade newFileName;

    @FindBy(name="text")
    private WebElementFacade fileContent;

    @FindBy(xpath="//*[@id=\"EditForm\"]/table/tbody/tr[1]/td[1]/a[2]/img")
    private WebElementFacade saveButton;

    @FindBy(xpath="//*[@id=\"EditForm\"]/table/tbody/tr[1]/td[1]/a[1]")
    private WebElementFacade backButtonBtn;

    @FindBy(xpath ="//*[@id=\"EditForm\"]/table/tbody/tr[1]/td[3]/span")
    private WebElementFacade statusMessage;

    public void enter_file_content(String content) {
        fileContent.type(content);
    }

    public void enter_file_name(String filename) {
        newFileName.type(filename);
    }

    public void click_to_create_File() {
        saveButton.click();
    }

    public void back() {
        backButtonBtn.click();
    }

    public String getStatusMessage() {
        return statusMessage.getText();
    }
}