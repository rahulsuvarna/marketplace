Feature: Get offers for a merchant
  Scenario: User calls Get Offers webservice using merchant id
    Given an offer exists for a merchant with a merchant id 3
    When a user retrieves the offer by merchant id 3
    Then the status code is 200
