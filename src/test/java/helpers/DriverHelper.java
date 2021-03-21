package helpers;

import com.codeborne.selenide.Configuration;
import config.WebDriverConfigHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static config.WebDriverConfigHelper.*;

public class DriverHelper {

    public static void configureSelenide() {
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browserSize = "1600x1200";
        Configuration.baseUrl = getBaseUrl();
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 45000;
        Configuration.pageLoadStrategy = "eager";
//        Configuration.headless = true;
        Configuration.clickViaJs = true;
        Configuration.fastSetValue = true;
        Configuration.screenshots = false;

        switch (getBrowserName()) {
            case "ff":
                Configuration.browser = "firefox";
                break;
            case "chrome":
                Configuration.browser = "chrome";
                options.addArguments("--disable-gpu");
                break;
            default:
                Configuration.browser = "chrome";
                return;
        }

        if (isVideoOn()) {
            Configuration.headless = false;
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            capabilities.setCapability("videoFrameRate", 24);
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        if (isRemoteWebDriver()) {
            Configuration.screenshots = true;
            Configuration.remote = getRemoteDriverUrl();
        }
    }
}
