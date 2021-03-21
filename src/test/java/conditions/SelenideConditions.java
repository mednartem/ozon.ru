package conditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SelenideConditions {

    public static Condition exactTextOnlyNumbers(final String text) {
        return new Condition(text) {
            @Override
            public boolean apply(Driver driver, WebElement element) {
//                return element.getText().replaceAll("\\D", "").equals(text.replaceAll("\\D", ""));
                return element.getText().replaceAll("\\D", "").equals(text);
            }

            public String actualValue(Driver driver, WebElement element) {
                return String.format("Element should have text %s", text.replaceAll("\\D", ""));
            }
        };
    }
}

