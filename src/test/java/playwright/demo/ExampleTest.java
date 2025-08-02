package playwright.demo;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class ExampleTest {
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
    void checkTitle() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://example.com");
        Assertions.assertEquals("Example Domain", page.title());
        page.close();
        context.close();
    }
}
