package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class ViewFilePage extends PageObject {
    @FindBy(xpath="//*[@id=\"ViewForm\"]/a")
    private WebElementFacade backButtonBtn;

    @FindBy(xpath = "//*[@id=\"ViewForm\"]/div[1]/table/tbody/tr/td[2]/pre/span")
    private WebElementFacade fileContent;

    public void back() {
        backButtonBtn.click();
    }

    public String getFileContent() {
        return fileContent.getText();
    }

}
