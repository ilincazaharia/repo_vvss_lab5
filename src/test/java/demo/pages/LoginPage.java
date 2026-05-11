package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.junit.Assert;

@DefaultUrl("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php")
public class LoginPage extends PageObject {

    @FindBy(name = "ftpserver")
    private WebElementFacade ftpServer;

    @FindBy(name = "username")
    private WebElementFacade username;

    @FindBy(name = "password")
    private WebElementFacade password;

    @FindBy(name = "Login")
    private WebElementFacade loginButton;

    public void select_server(String serverName) {
        ftpServer.clear();
        ftpServer.type(serverName);
    }

    public void enter_username(String userName) {
        username.clear();
        username.type(userName);
    }

    public void enter_password(String passwordValue) {
        password.clear();
        password.type(passwordValue);
    }

    public void click_Login() {
        loginButton.click();
    }

    public void saveCookiesIfPresent() {
        if (findAll(By.id("LoginButton1")).size() > 0) {
            find(By.id("LoginButton1")).click();
        }
    }

    public void should_contain_text(String text) {
        Assert.assertTrue(
                "Textul nu apare in pagina: " + text,
                find(By.tagName("body")).getText().contains(text)
        );
    }

    public String getPageText() {
        return find(By.tagName("body")).getText();
    }
}
