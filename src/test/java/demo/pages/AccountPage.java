package demo.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class AccountPage extends PageObject {

    public String getPageSourceText() {
        return getDriver().getPageSource();
    }

    public String getBodyText() {
        return find(By.tagName("body")).getText();
    }

    public boolean isUserLoggedIn() {
        String source = getPageSourceText();
        String body = getBodyText();

        return source.contains("BrowseForm")
                || source.contains("StatusbarForm")
                || body.contains("Logout")
                || body.contains("New dir")
                || body.contains("Upload");
    }

    public void click_Logout() {
        clickFirstMatchingElement(
                "//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'logout')]",
                "//img[contains(translate(@alt, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'logout')]/parent::a",
                "//img[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'logout')]/parent::a"
        );
    }

    public void click_new_directory() {
        clickFirstMatchingElement(
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'new dir')]",
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'new directory')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'new dir')]",
                "//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'new dir')]",
                "//img[contains(translate(@alt, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'new dir')]/parent::a",
                "//img[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'new dir')]/parent::a",
                "//img[contains(translate(@alt, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'directory')]/parent::a",
                "//img[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'directory')]/parent::a"
        );
    }

    public void enter_new_directory_name(String directoryName) {
        List<WebElement> inputs = getDriver().findElements(By.xpath(
                "//input[contains(@name, 'new') " +
                        "or contains(@name, 'New') " +
                        "or contains(@name, 'dir') " +
                        "or contains(@name, 'Dir') " +
                        "or contains(@name, 'directory') " +
                        "or contains(@name, 'Directory')]"
        ));

        for (WebElement input : inputs) {
            try {
                if (input.isDisplayed() && input.isEnabled()) {
                    input.clear();
                    input.sendKeys(directoryName);
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        List<WebElement> allInputs = getDriver().findElements(By.xpath(
                "//input[not(@type='hidden') and not(@type='submit') and not(@type='button') and not(@type='image') and not(@type='file')]"
        ));

        for (WebElement input : allInputs) {
            try {
                String name = input.getAttribute("name");
                String value = input.getAttribute("value");

                boolean isNotLoginField =
                        name == null ||
                                (!name.equalsIgnoreCase("ftpserver")
                                        && !name.equalsIgnoreCase("username")
                                        && !name.equalsIgnoreCase("password")
                                        && !name.equalsIgnoreCase("directory"));

                boolean isEmpty = value == null || value.trim().isEmpty();

                if (input.isDisplayed() && input.isEnabled() && isNotLoginField && isEmpty) {
                    input.clear();
                    input.sendKeys(directoryName);
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        throw new AssertionError("Nu am gasit input pentru numele directorului. Pagina curenta este: " + getBodyText());
    }

    public void submit_new_directory() {
        List<WebElement> submitElements = getDriver().findElements(By.xpath(
                "//input[@type='submit' or @type='image' or @name='Submit' or @name='SubmitButton' or @value='Create' or @value='Submit']"
        ));

        if (!submitElements.isEmpty()) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", submitElements.get(0));
            return;
        }

        List<WebElement> forms = getDriver().findElements(By.tagName("form"));

        for (WebElement form : forms) {
            if (form.getText().contains("New directory")
                    || form.getAttribute("innerHTML").contains("new")
                    || form.getAttribute("innerHTML").contains("directory")) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].submit();", form);
                return;
            }
        }

        if (!forms.isEmpty()) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].submit();", forms.get(0));
            return;
        }

        throw new AssertionError("Nu am gasit formularul pentru crearea directorului. Pagina curenta este: " + getBodyText());
    }

    public void upload_file(String filePath) {
        clickFirstMatchingElement(
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'upload')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'upload')]",
                "//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'upload')]",
                "//img[contains(translate(@alt, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'upload')]/parent::a",
                "//img[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'upload')]/parent::a"
        );

        File file = new File(filePath);

        WebElement fileInput = findFirstDisplayedElement(
                "//input[@type='file']"
        );

        fileInput.sendKeys(file.getAbsolutePath());

        clickFirstMatchingElement(
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'upload')]",
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'submit')]",
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'ok')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'upload')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'submit')]",
                "//input[@type='submit']"
        );
    }

    public void check_item_to_delete(String itemName) {
        clickFirstMatchingElement(
                "//tr[contains(., '" + itemName + "')]//input[@type='checkbox']",
                "//input[contains(@value, '" + itemName + "')]",
                "//a[contains(., '" + itemName + "')]/ancestor::tr//input[@type='checkbox']"
        );
    }

    public void delete_selected_item() {
        clickFirstMatchingElement(
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]",
                "//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]",
                "//img[contains(translate(@alt, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]/parent::a",
                "//img[contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]/parent::a"
        );
    }

    public void confirm_delete_directory() {
        clickFirstMatchingElement(
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'confirm')]",
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]",
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'yes')]",
                "//input[contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'ok')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'confirm')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'delete')]",
                "//button[contains(., 'Yes')]",
                "//input[@type='submit']"
        );
    }

    public List<String> getContent() {
        return getDriver().findElements(By.tagName("body"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean pageContainsText(String text) {
        return getBodyText().contains(text) || getPageSourceText().contains(text);
    }

    private void clickFirstMatchingElement(String... xpaths) {
        WebElement element = findFirstDisplayedElement(xpaths);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    private WebElement findFirstDisplayedElement(String... xpaths) {
        for (String xpath : xpaths) {
            List<WebElement> elements = getDriver().findElements(By.xpath(xpath));

            for (WebElement element : elements) {
                try {
                    if (element.isDisplayed() && element.isEnabled()) {
                        return element;
                    }
                } catch (Exception ignored) {
                }
            }
        }

        throw new AssertionError("Nu am gasit niciun input/button pentru actiunea ceruta. Pagina curenta este: " + getBodyText());
    }

    public void go_back_to_file_list() {
        List<WebElement> links = getDriver().findElements(By.xpath(
                "//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'back') " +
                        "or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'go back') " +
                        "or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'browse') " +
                        "or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'directory')]"
        ));

        for (WebElement link : links) {
            try {
                if (link.isDisplayed() && link.isEnabled()) {
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", link);
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        getDriver().navigate().back();
    }
}