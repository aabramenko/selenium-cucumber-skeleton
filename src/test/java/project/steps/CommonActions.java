package project.steps;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import project.core.XmlManager;
import project.data.TestData;
import project.pages.example.GoogleSearchPage;

public class CommonActions extends AbstractSteps {

    private Logger log = Logger.getLogger("");

    public GoogleSearchPage openGoogleSearchPage() {
        log.info("open Google start page");
        getDriver().get(TestData.GOOGLE_START_PAGE);
        return new GoogleSearchPage(getDriver());
    }

    String getCurrencyAmountFromBank(String currencyName) {
        log.info("getting amount of " + currencyName + " from bank");
        String url = TestData.URL_XML_TODAYS_RATES_FROM_BANK;
        Document doc = XmlManager.uploadXmlFromUrl(url);
        String amount = XmlManager.getValueByXpath(
                doc,
                TestData.XML_XPATH_GET_RATE_BY_CURRENCY_NAME.replace("{NAME}", currencyName.toUpperCase())
        );
        log.info("bank api: " + currencyName + ": Currency rate = " + amount);
        return amount;
    }

}
