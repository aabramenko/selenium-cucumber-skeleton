package project.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import project.pages.example.GoogleResultsPage;
import project.pages.example.GoogleSearchPage;

public class CurrencyRateActions extends CommonActions {

    GoogleSearchPage searchPage;
    GoogleResultsPage resultsPage;
    double delta = 0.05;

    @Given("^I as not logged in user open search page$")
    public void iAsNotLoggedInUserOpenSearchPage() {
        searchPage = openGoogleSearchPage();
    }

    @Then("I on result page should see same rate as given from bank for {string}")
    public void iOnResultPageShouldSeeSameRateAsGivenFromBankFor(String currencyTo) {
        String amountFromGoogle = resultsPage.getConverterAreaAmountToText();
        String amountFromBank = getCurrencyAmountFromBank(currencyTo);

        Assert.assertTrue(
                Math.abs(Double.parseDouble(amountFromGoogle) - Double.parseDouble(amountFromBank)) <= delta,
                "Rate of " + amountFromGoogle + " and " + amountFromBank + " are not the same"
        );
    }

    @When("I on search page submit {string}{string}")
    public void iOnSearchPageSubmit(
            String baseText, String currencyTo
    ) {
        resultsPage = searchPage.search(baseText + currencyTo);
    }
}
