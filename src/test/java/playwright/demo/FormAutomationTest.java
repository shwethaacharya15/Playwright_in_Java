package playwright.demo;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

import org.junit.jupiter.api.*;

public class FormAutomationTest {
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
void fillPracticeForm() {
    BrowserContext context = browser.newContext();
    Page page = context.newPage();
    
    page.setDefaultNavigationTimeout(60000); // Fix #1
    page.navigate("https://demoqa.com/automation-practice-form", 
        new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED)); // Fix #2
    
    page.waitForSelector("#firstName"); // Fix #3
    page.locator("#firstName").fill("Shwetha");

    // continue with the rest of the form...
    
    page.close();
    context.close();
}

}
