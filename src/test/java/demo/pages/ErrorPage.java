package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class ErrorPage extends PageObject {

    @FindBy(tagName = "body")
    private WebElementFacade body;

    public String getPageText() {
        return body.getText();
    }
}