package playwright.demo.tests;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;


public class LoginTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://practicetestautomation.com/practice-test-login/");
            // page.waitForLoadState(LoadState.LOAD); // Wait for full page load

            page.waitForSelector("#username").fill("student");
            page.fill("#password", "Password123");

            page.click("#submit");

            // Wait for URL to confirm login success
            page.waitForURL("**/logged-in-successfully/");

            // Assert login was successful
            String successMsg = page.textContent("h1");
            System.out.println("Login Message: " + successMsg);

            browser.close();
        }
    }
}
