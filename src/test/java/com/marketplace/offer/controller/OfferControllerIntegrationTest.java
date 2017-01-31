package com.marketplace.offer.controller;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marketplace.config.StandardAPIError;
import com.marketplace.offer.dto.OfferDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class OfferControllerIntegrationTest {

	private static final String ADD_URI = "/offers/add";
	private static final String FIND_URI = "/offers/all";
	@Autowired
	private TestRestTemplate restTemplate;
	@Before
	public void setUp() throws Exception {
		
	}


	@Test
	public void testFindAllOffers() throws Exception {
		
		ResponseEntity<OfferDTO[]> response = restTemplate.getForEntity(FIND_URI, OfferDTO[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response, notNullValue());
		OfferDTO[] body = response.getBody();
		assertThat(body, notNullValue());
		assertThat(body.length, is(3));
        
	}
	@Test
	public void testAddOfferSuccess() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");
		List<OfferDTO> lOfOffers = Arrays.asList(
				new OfferDTO( "Title1", "Description", 100057L, 1L, validFromDate, validToDate),
				new OfferDTO( "Title2", "Description", 100057L, 2L, validFromDate, validToDate));
		ResponseEntity<OfferDTO[]> response = restTemplate.postForEntity(ADD_URI, lOfOffers, OfferDTO[].class);
		assertThat(response, notNullValue());
		OfferDTO[] body = response.getBody();
		assertNull(body);
		
	}

	@Test
	public void testAddOfferFailure() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");
		List<OfferDTO> lOfOffers = Arrays.asList(
				new OfferDTO( "Title1", "Description", 1L, 1L, validFromDate, validToDate),
				new OfferDTO( "Title2", "Description", 100057L, 2L, validFromDate, validToDate));
		ResponseEntity<StandardAPIError> response = restTemplate.postForEntity(ADD_URI, lOfOffers, StandardAPIError.class);
		assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
	}

}
