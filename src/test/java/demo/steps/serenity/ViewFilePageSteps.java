package demo.steps.serenity;

import demo.pages.ViewFilePage;
import net.thucydides.core.annotations.Step;

public class ViewFilePageSteps {
    ViewFilePage viewFilePage;

    @Step
    public void back() {
        viewFilePage.back();
    }

    @Step
    public void should_be_able_to_see_content(String text) {
        assert(viewFilePage.getFileContent().contains(text));
    }
}
