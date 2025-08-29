package playwright.demo;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;

import org.junit.jupiter.api.*;

public class ultimateqa {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    

    @BeforeAll
    static void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

    }

       @BeforeEach
    void createContextAndPage() {
        BrowserContext context = browser.newContext();
        context = browser.newContext();
        page = context.newPage();

        page.setDefaultNavigationTimeout(120000);

        Page.NavigateOptions options = new Page.NavigateOptions();
        options.waitUntil = WaitUntilState.LOAD;

        page.navigate("https://courses.ultimateqa.com/collections", options);
    }

      @AfterEach
    void cleanup() {
        BrowserContext context = browser.newContext();
        context.close();
    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    void TestCase1() {
        
        Locator signInLink = page.locator("a[href='/users/sign_in']");
        signInLink.click(new Locator.ClickOptions().setForce(true));

        // page.locator("nav").getByText("Sign In").click();
        assertEquals("https://courses.ultimateqa.com/users/sign_in", page.url());
       
    }

    @Test
    void TestCase2() {
        Locator signInLink = page.locator("a[href='/users/sign_in']");
        signInLink.click(new Locator.ClickOptions().setForce(true));

       
        assertEquals("https://courses.ultimateqa.com/users/sign_in", page.url());
        assertTrue(page.locator("body").innerText().contains("Welcome"));

        Locator Emailfield = page.locator("input[id='user[email]']");
        Emailfield.click();

        // Emailfield.fill("shwetha@gmail.com");

        Locator Passfield = page.locator("input[id='user[password]']");
        Passfield.click();

        Locator Emailerror = page.locator("p:has-text('Please enter a valid email address')");
        Emailerror.waitFor();
        assertEquals("Please enter a valid email address", Emailerror.innerText());
    }
}
