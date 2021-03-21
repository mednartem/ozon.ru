package helpers;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.by;

public class Selectors {

    public static By byDataWidget(String dataWidget) {
        return by("data-widget", dataWidget);
    }
}
