package playwright.demo;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class LocatorPlaygroundTest {
    static Playwright playwright;
    static Browser browser;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    void testAllLocators() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        
        System.out.println("Navigating to Elements page...");

        page.navigate("https://demoqa.com/elements", new Page.NavigateOptions().setTimeout(60000));
        page.getByText("Check Box").click();

        page.navigate("https://demoqa.com/text-box");
        page.locator("#userName").fill("Shwetha QA");
        page.locator("button:has-text('Submit')").click();
        page.locator("button").nth(1).click();

        page.navigate("https://demoqa.com/");
        Locator firstCard = page.locator(".card").first();
        Locator cardTitle = firstCard.locator("h5");
        System.out.println("First Card: " + cardTitle.textContent());

        page.close();
        context.close();
    }
}
