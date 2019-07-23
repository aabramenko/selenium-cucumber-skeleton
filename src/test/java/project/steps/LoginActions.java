package project.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import project.pages.example.GoogleSignInEmailPage;
import project.pages.example.GoogleSearchPage;
import project.pages.example.GoogleSignInPasswordPage;

public class LoginActions extends CommonActions {

    GoogleSearchPage searchPage;
    GoogleSignInEmailPage signInEmailPage;
    GoogleSignInPasswordPage signInPasswordPage;

    @Given("I as not logged in user (open|navigate to) login page")
    public void iAsNotLoggedInUserOpenLoginPage() {
        searchPage = openGoogleSearchPage();
        signInEmailPage = searchPage.clickSignInButton();
    }

    @When("I on login page submit absent email {string}")
    public void iOnLoginPageSubmitAbsentEmail(String email) {
        signInEmailPage.printEmail(email);
        signInEmailPage = signInEmailPage.clickNextButtonWrongEmail();
    }

    @Then("I on login page should see error message {string}")
    public void iOnLoginPageShouldSeeErrorMessage(String errorText) {
        Assert.assertTrue(
                signInEmailPage.isErrorNoticeDisplayed(errorText),
                errorText + " notification is displayed"
        );
    }

    @And("email input is still displayed")
    public void emailInputIsStillDisplayed() {
        Assert.assertTrue(
                signInEmailPage.isEmailInputDisplayed(),
                "Email input is still displayed"
        );
    }

    @When("I on login page submit existing email {string}")
    public void iOnLoginPageSubmitExistingEmail(String email) {
        signInEmailPage.printEmail(email);
        signInPasswordPage = signInEmailPage.clickNextButton();
    }

    @Then("I on login page should see password input")
    public void iOnLoginPageShouldSeePasswordInput() {
        Assert.assertTrue(
                signInPasswordPage.isPasswordInputDisplayed(),
                "password input is displayed"
        );
    }
}
