package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import model.SmartphoneModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static conditions.SelenideConditions.exactTextOnlyNumbers;
import static helpers.Selectors.byDataWidget;
import static io.qameta.allure.Allure.step;

public class OzonTests extends TestBase {

    @Test
    @AllureId("1733")
    @Owner("Demyshev")
    @Feature("Main page")
    @Tag("mainPage")
    @DisplayName("Valid open main page")
    void test001MainPageTest() {
        step("Open main page", () -> open(""));
        step("Check open main page", () -> {
            $(byName("search")).shouldBe(visible);
        });
    }

    @Test
    @Owner("Demyshev")
    @Feature("Search")
    @Tags({@Tag("smartphones"), @Tag("search")})
    @DisplayName("Successfully search")
    void test002checkSearch() {
        String textForSearch = "iPhone";
        String resultTitles = ".a2g0.tile-hover-target";

        step("Open main page", () -> open(""));
        step(String.format("Enter text %s to filed search", textForSearch), () -> {
            $(byName("search")).setValue(textForSearch).pressEnter();
        });
        step("Check all results has text " + textForSearch, () -> {
            $(byDataWidget("searchResultsV2")).shouldBe(visible)
                    .$$(resultTitles).shouldHave(
                    allMatch(
                            "All results must have " + textForSearch,
                            element -> element.getText().contains(textForSearch)
                    ));
        });
    }

    @Test
    @Owner("Demyshev")
    @Feature("Smartphones")
    @Tag("smartphones")
    @DisplayName("Check list smartphones isn't empty")
    void test003checkListSmartphonesOfCatalogIsNotEmpty() {
        step("Open Main page", () -> open(""));
        step("Choose category smartphones of the catalog", () -> {
            $x("//div[@data-widget=\"catalogMenu\"]//button").click();
            $(byText("Смартфоны")).click();
        });
        step("Check list smartphones isn't empty", () -> {
            $(byDataWidget("searchResultsV2")).$$(".a0c6").shouldHave(sizeGreaterThan(0));
        });
    }

    @Test
    @Owner("Demyshev")
    @Feature("Smartphones")
    @Tag("smartphones")
    @DisplayName("Add smartphone to the shopping cart")
    void test004makeOrder() {
        SmartphoneModel model = new SmartphoneModel();
        String listPhones = ".a0c6";
        String firstTitleOfPhoneOnPage = ".a2g0.tile-hover-target";
        String firstPriceOnPage = ".b5v4.item> span";
        String titleOfPhoneOnCart = ".a7n3 > span";
        String priceOfPhoneOnCart = ".a7o7 > span";
        String totalPriceInCart = ".a5a6";

        step("Open Parcel page", () -> open("category/smartfony-15502/"));
        step("Add to shopping cart", () -> {
            ElementsCollection listSmartphones = $(byDataWidget("searchResultsV2")).$$(listPhones).shouldHave(sizeGreaterThan(0));
            model.setTitle(listSmartphones.first().$(firstTitleOfPhoneOnPage).getText());
            model.setPrice(listSmartphones.first().$(firstPriceOnPage).getText().replaceAll("\\D", ""));
            $(byText("В корзину")).click();
        });
        step("Open shopping cart", () -> {
            $(byDataWidget("cart")).shouldHave(text("1")).click();
        });
        step("Check shopping cart has added smartphone", () -> {
            // First way for check
//            $(byDataWidget("split")).shouldHave(text(model.getTitle()), text(model.getPrice().replaceFirst("\\D", "")));
//            $(byDataWidget("total")).shouldHave(text(model.getPrice().replaceFirst("\\D", "")));

            // Second way for check
            SelenideElement phone = $(byDataWidget("split"));
            phone.$(titleOfPhoneOnCart).shouldHave(text(model.getTitle()));
            phone.$(priceOfPhoneOnCart).shouldHave(exactTextOnlyNumbers(model.getPrice()));
            $(byDataWidget("total")).$(totalPriceInCart).shouldHave(exactTextOnlyNumbers(model.getPrice()));
        });
    }
}
