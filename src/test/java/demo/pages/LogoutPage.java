package demo.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class LogoutPage extends PageObject {

    public String getPageText() {
        return find(By.tagName("body")).getText();
    }
}
