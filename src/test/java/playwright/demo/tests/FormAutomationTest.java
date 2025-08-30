package playwright.demo.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

import java.nio.file.Paths;

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
    
    page.setDefaultNavigationTimeout(60000);
 

    Page.NavigateOptions options = new Page.NavigateOptions();
        
    options.waitUntil = WaitUntilState.NETWORKIDLE; 
    page.navigate("https://demoqa.com/automation-practice-form", options);

    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("form_debug.png")));




    
    page.waitForSelector("#firstName"); 
    page.locator("#firstName").fill("Shwetha");

    page.locator("#lastName").fill("Acharya");



    
    page.close();
    context.close();
}

}
