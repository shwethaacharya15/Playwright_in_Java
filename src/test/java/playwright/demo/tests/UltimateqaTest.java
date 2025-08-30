package playwright.demo.tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import playwright.demo.pages.SignIn;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UltimateqaTest {
    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void setupClass() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    void setup() {
        page = browser.newPage();
        page.navigate("https://courses.ultimateqa.com/users/sign_up");
    }

    @Test
    void testCreateAccount() {
        SignIn signin = new SignIn(page);

        page.locator("a[href='/users/sign_up']").click();
        page.waitForURL("**/users/sign_up", new Page.WaitForURLOptions().setTimeout(10000));
        Assertions.assertTrue(page.url().contains("/users/sign_up"), "Sign Up page did not load correctly");


        signin.enterDetails("Shwetha", "shwetha@example.com", "Password123!");
        signin.submitForm();
    }

    @AfterEach
    void tearDown() {
        page.close();
    }

    @AfterAll
    static void cleanup() {
        browser.close();
        playwright.close();
    }
}

