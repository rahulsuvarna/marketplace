package com.marketplace.offer.repository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.marketplace.offer.dto.OfferDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DataJpaTest(showSql=true)
@ActiveProfiles(value="test")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data-test-h2.sql")
public class OfferRepositoryIntegrationTest {

	private final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private OfferRepository offerRepository;
	@Before
	public void setUp() throws Exception {	
		
		offerRepository.deleteAll();
	}

	@Test
	public void testSaveOfferSuccess() throws Exception {
		final Date validFromDate = dateformat.parse("2017-01-30");
		final Date validToDate = dateformat.parse("2017-02-20");
		
		final OfferDTO offerDTO = new OfferDTO( "Title1", "Description", 100057L, 1L, 200057L, validFromDate, validToDate);
		final OfferDTO savedOffer = offerRepository.save(offerDTO);

		assertThat(savedOffer, notNullValue());
		assertThat(savedOffer.getId(), notNullValue());
		assertThat(savedOffer.getTitle(), is("Title1"));
		assertThat(savedOffer.getDescription(), is("Description"));
		assertThat(savedOffer.getTypeId(), is(100057L));
		assertThat(savedOffer.getMerchantId(), is(1L));
	}

	@Test
	public void testSaveOfferFailure() throws Exception {
		final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		final Date validFromDate = dateformat.parse("2017-01-30");
		final Date validToDate = dateformat.parse("2017-02-20");
		
		final OfferDTO offerDTO = new OfferDTO( "Title1", "Description", 2L, 1L, 1L, validFromDate, validToDate);
		
		assertThatThrownBy(() -> offerRepository.save(offerDTO)).isInstanceOf(DataIntegrityViolationException.class);
	}

}
