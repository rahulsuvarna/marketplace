package com.marketplace.cucumber;

import java.io.IOException;
import java.util.List;

import com.marketplace.offer.dto.MerchantDTO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class MerchantsTestSteps extends SpringIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(MerchantsTestSteps.class);
    private ResponseEntity<MerchantDTO> response;
    @Autowired(required=true)
    private TestRestTemplate restTemplate;

    @Given("^The Add Merchant REST API exits$")
    public void theAddMerchantRESTAPIexists() {
        log.info("msg");
    }

    @When("^Client posts a request to Add a merchant with$")
    public void clientPostsARequest(DataTable dataTable) throws IOException {
        List<List<String>> data = dataTable.raw();
        MerchantDTO merchant = new MerchantDTO(null, data.get(2).get(2), data.get(1).get(2), data.get(3).get(2));
        org.springframework.http.HttpEntity<MerchantDTO> entity = new org.springframework.http.HttpEntity<MerchantDTO>(merchant);
        response = restTemplate.exchange("/merchants",
                    HttpMethod.POST, entity, new ParameterizedTypeReference<MerchantDTO>() {
            });
    }

    @Then("^the client receives a response code of 200$")
    public void requestIsSuccessfull() throws IOException {
        assertThat(response.getStatusCodeValue(), is(200));
        System.out.println(response.getBody());
        assertThat(response.getBody().getName(), is("Ernest Jones"));
        assertThat(response.getBody().getCode(), is("EMA"));
        assertThat(response.getBody().getCurrencyCode(), is("INR"));
    }



    private void usingHTTP(DataTable dataTable) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:"+port+"/merchants");
        List<List<String>> data = dataTable.raw();
        String json = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate(data.get(0).get(0), data.get(0).get(2));
        jsonObject.accumulate(data.get(1).get(0), data.get(1).get(2));
        jsonObject.accumulate(data.get(2).get(0), data.get(2).get(2));
        json = jsonObject.toString();
        StringEntity se = new StringEntity(json);
        httpPost.setEntity(se);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = httpclient.execute(httpPost);
        // HttpPost request = new HttpPost("http://localhost:8080/merchants/");
        // HttpClient httpClient = HttpClients.createDefault();
        // HttpResponse httpResponse = httpClient.execute(request);

        HttpEntity entity = httpResponse.getEntity();
        String responseBody = EntityUtils.toString(entity);
    }

    private void usingRestTemplate(DataTable dataTable) throws IOException {
        ResponseEntity<List> response = restTemplate.getForEntity("/merchants", List.class);

        List<List<String>> data = dataTable.raw();
        MerchantDTO merchant = new MerchantDTO(null, data.get(0).get(2), data.get(1).get(2), data.get(2).get(2));
        org.springframework.http.HttpEntity<MerchantDTO> entity = new org.springframework.http.HttpEntity<MerchantDTO>(merchant);
        ResponseEntity<List<MerchantDTO>> secondResponse = restTemplate.exchange("/merchants",
                    HttpMethod.POST, entity, new ParameterizedTypeReference<List<MerchantDTO>>() {
            });
        List<MerchantDTO> lOfMerchantDTOs = secondResponse.getBody();
    }

}