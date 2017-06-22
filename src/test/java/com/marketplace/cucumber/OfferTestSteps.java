package com.marketplace.cucumber;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;

/**
 * Created by vsrah on 23/04/2017.
 */
public class OfferTestSteps {

    public static final int PORT_NUMBER = 8089;
    public static final int STATUS_CODE = 200;
    public static final String SERVER_NAME = "localhost";
    private WireMockServer wireMockServer = null;
    private HttpResponse httpResponse = null;
    private CloseableHttpClient httpClient = null;

    @Given("^an offer exists for a merchant with a merchant id (\\d+)$")
    public void anOfferExistsForAMerchantWithAMerchantId(int merchantId) throws Throwable {

        wireMockServer = new WireMockServer(wireMockConfig().port(PORT_NUMBER));
        wireMockServer.start();
        configureFor(SERVER_NAME, wireMockServer.port());

        stubFor(get(urlEqualTo("/merchants/"+merchantId+"/offers"))
                .willReturn(aResponse().withStatus(STATUS_CODE)));
        httpClient = HttpClients.createDefault();
        Assert.assertTrue(true);
    }

    @When("^a user retrieves the offer by merchant id (\\d+)$")
    public void aUserRetrivesTheOfferByMerchantId(int merchantId) throws Throwable {
        HttpGet request = new HttpGet("http://"+SERVER_NAME+":"+PORT_NUMBER+"/merchants/"+merchantId+"/offers");
        httpResponse = httpClient.execute(request);
    }

    @Then("^the status code is (\\d+)$")
    public void theStatusCodeIs(int statusCode) throws Throwable {
        assertEquals(statusCode, httpResponse.getStatusLine().getStatusCode());
    }
}
