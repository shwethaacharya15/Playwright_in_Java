package playwright.demo.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page.LocatorOptions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
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
        BrowserContext context = browser.newContext();
        context = browser.newContext();
        page = context.newPage();

        page.setDefaultNavigationTimeout(120000);

        Page.NavigateOptions options = new Page.NavigateOptions();
        options.waitUntil = WaitUntilState.LOAD;

        page.navigate("https://courses.ultimateqa.com/collections", options);

    }

    //    @BeforeEach
    // void createContextAndPage() {
        
    // }

    //   @AfterEach
    // void cleanup() {
       
    // }

    @AfterAll
    static void teardown() {
        BrowserContext context = browser.newContext();
        context.close();
        browser.close();
        playwright.close();
    }

    @Test
    void TestCase1() {
        
        Locator signInLink = page.locator("a[href='/users/sign_in']");
        signInLink.click(new Locator.ClickOptions().setForce(true));

        // page.locator("nav").getByText("Sign In").click();
        assertEquals("https://courses.ultimateqa.com/users/sign_in", page.url());
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshots/signin-failed.png")));
       
    }

    @Test
    void TestCase2() {
        Locator signInLink = page.locator("a[href='/users/sign_in']");
        signInLink.click(new Locator.ClickOptions().setForce(true));

       
        assertEquals("https://courses.ultimateqa.com/users/sign_in", page.url());
        assertTrue(page.locator("body").innerText().contains("Welcome"));
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshots/welcometext-failed.png")));

        Locator Emailfield = page.locator("input[id='user[email]']");
        Emailfield.click();

        // Emailfield.fill("shwetha@gmail.com");

        Locator Passfield = page.locator("input[id='user[password]']");
        Passfield.click();

        Locator Emailerror = page.locator("p:has-text('Please enter a valid email address')");
        Emailerror.waitFor();
        assertEquals("Please enter a valid email address", Emailerror.innerText());
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshots/email error message-failed.png")));
    }


      @Test
    void TestCase3() {
        Locator signInLink = page.locator("a[href='/users/sign_in']");
        signInLink.click(new Locator.ClickOptions().setForce(true));

        Locator Emailfield = page.locator("input[id='user[email]']");
        Emailfield.click();
        Emailfield.fill("Shwetha@gmail.com");

        Locator Passfield = page.locator("input[id='user[password]']");
        Passfield.click();
        Passfield.fill("Shwetha@28");

        Locator Rememberme = page.locator("input[id='user[remember_me]']");
        Rememberme.check();

        Locator SignInButton = page.locator("//button[@type='submit']");
        SignInButton.click();

    }

    @Test
    void createAccount() {

        Locator signInLink = page.locator("a[href='/users/sign_in']");
        signInLink.click(new Locator.ClickOptions().setForce(true));
        
       page.locator("a[href='/users/sign_up']").click();
       page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshots/signup-failed.png")));

// Wait for navigation
page.waitForURL("**/users/sign_up", new Page.WaitForURLOptions().setTimeout(60000));
page.locator("#user_accepts_terms").check();


// Assert URL
assertTrue(page.url().startsWith("https://courses.ultimateqa.com/users/sign_up"));

// Assert heading text
String headingText = page.locator("h1.page__heading").innerText();
System.out.println("Heading text: " + headingText);
assertTrue(headingText.contains("Create a new account"));



        Locator firstNameLabel = page.locator("label[for='user[first_name]']");
        firstNameLabel.waitFor();
        assertTrue(firstNameLabel.isVisible());
        page.locator("input[id='user[first_name]']").fill("Shwetha");

        Locator lastNameLabel = page.locator("label[for='user[last_name]']");
        lastNameLabel.waitFor();
        assertTrue(lastNameLabel.isVisible());
        page.locator("input[id='user[last_name]']").fill("Acharya");

        Locator EmailLabel = page.locator("label[for='user[email]']");
        EmailLabel.waitFor();
        assertTrue(EmailLabel.isVisible());
        page.locator("input[id='user[email]']").fill("shwetha@gmail.com");

        Locator PasswordLabel = page.locator("label[for='user[password]']");
        PasswordLabel.waitFor();
        assertTrue(PasswordLabel.isVisible());
        page.locator("input[id='user[password]']").fill("Shwetha@28");

        page.locator("input[id='user[terms]']").uncheck();

        page.locator("input[id='user[terms]']").check();

        page.locator("button[type='submit']").click();

    }
}
 