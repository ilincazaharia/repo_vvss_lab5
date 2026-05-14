package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;


public class AccountPage extends PageObject {

    @FindBy(css="#BrowseForm > input[type=text]:nth-child(24)")
    private WebElementFacade currentDirectory;

    @FindBy(xpath="//*[@id=\"StatusbarForm\"]/a[4]/img")
    private WebElementFacade logoutButton;

    @FindBy(css="#buttontable > tbody")
    private WebElementFacade buttonList;

    @FindBy(id = "maintable")
    private WebElementFacade listOfDirectories;


    public String getCurrentDirectoryName(){
        System.out.println(currentDirectory.getValue());
        return currentDirectory.getText();
    }

    public void click_Logout(){
        logoutButton.click();
    }


    public void click_new_file()
    {
        click_element_by_attribute_value(buttonList, "value", "New file");
    }

    public void click_edit_file(String fileName) {
        WebElementFacade editLink = find(
                By.xpath("//tr[.//a[text()='" + fileName + "']]//a[text()='Edit']")
        );
        editLink.click();
    }

    public void click_view_file(String fileName) {
        WebElementFacade editLink = find(
                By.xpath("//tr[.//a[text()='" + fileName + "']]//a[text()='View']")
        );
        editLink.click();
    }

    public void click_rename_file() {
        click_element_by_attribute_value(buttonList, "value", "Rename");
    }

    public void select_file(String fileName) {
        click_element_by_attribute_value(listOfDirectories, "value", fileName);
    }

    public List<String> getContent() {
        WebElementFacade definitionList = find(By.tagName("body"));
        return definitionList.findElements(By.tagName("a")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
    }


    public List<String> getContent2() {
        WebElementFacade definitionList = find(By.tagName("td"));
        return definitionList.findElements(By.tagName("a")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
    }

    private void click_element_by_attribute_value(WebElementFacade el, String attribute, String value) {
        //System.out.println(el);
        List<WebElement> list = el.findElements(By.tagName("input"));
        //list.forEach(System.out::println);
        for (int i = 0; i<list.size(); i++){
            String dir = list.get(i).getAttribute(attribute);
            if (dir.equalsIgnoreCase(value)) {
                list.get(i).click();
                break;
            }
        }
    }

    public void deleteFile() {

        click_element_by_attribute_value(buttonList, "value", "Delete");
    }

}