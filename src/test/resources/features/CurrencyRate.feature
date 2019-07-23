Feature: Google currency rate feature

  @Rate
  Scenario Outline: Validate exchange rate Google feature
    Given I as not logged in user open search page
    When I on search page submit "euro rate to " "<currencyTo>"
    Then I on result page should see same rate as given from bank for "<currencyTo>"

    Examples:
      | currencyTo  |
      | usd         |
      | rub         |
      | zar         |
      | cad         |
      | thb         |