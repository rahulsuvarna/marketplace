Feature: Test Merchants REST API

   Scenario: Add a merchant
   Given The Add Merchant REST API exits
   When Client posts a request to Add a merchant with
   | attribute  |type       |value          |
   | name       |string     |Ernest Jones   |
   |code        |string     |EMA            |
   |currencyCode|string     |INR            |
   Then the client receives a response code of 200
