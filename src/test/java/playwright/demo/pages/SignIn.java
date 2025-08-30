package playwright.demo.pages;
import com.microsoft.playwright.Page;

public class SignIn {

     private Page page;

      // Locators
    private String signUpHeader = "h1"; 
    private String firstNameInput = "#user[first_name]";
    private String emailInput = "#user[email]";
    private String passwordInput = "#user[password]";
    private String submitButton = "input[type='submit']";

    // Constructor
    public SignIn(Page page) {
        this.page = page;
    }
    

    // Actions
    public boolean isSignUpPageLoaded() {
        return page.locator(signUpHeader).innerText().contains("Create a new account");
    }

    public void enterDetails(String name, String email, String password) {
        page.fill(firstNameInput, name);
        page.fill(emailInput, email);
        page.fill(passwordInput, password);
    }

    public void submitForm() {
        page.click(submitButton);
    }
}
    
