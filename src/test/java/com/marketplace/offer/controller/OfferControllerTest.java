package com.marketplace.offer.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.config.GlobalExceptionHandler;
import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.service.IOfferService;

@SpringBootTest
public class OfferControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private OfferController offerController;
    
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;
    
    @Mock
    private IOfferService offerService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
                .standaloneSetup(offerController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

	@Test
	public void testBulkAddOfferSuccess() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");
		List<OfferDTO> lOfOffers = Arrays.asList(
				new OfferDTO( "Title1", "Description", 1L, 1L, validFromDate, validToDate),
				new OfferDTO( "Title2", "Description", 1L, 2L, validFromDate, validToDate));
		
		List<OfferDTO> actual = Arrays.asList(
				new OfferDTO(1L, "Title1", "Description", 1L, 1L, validFromDate, validToDate),
				new OfferDTO(2L, "Title2", "Description", 1L, 2L, validFromDate, validToDate));

		when(offerService.bulkAdd(any())).thenReturn(actual);
		
		mockMvc.perform(
                post("/offers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(lOfOffers)))
						.andExpect(status().isCreated());
		verify(offerService, times(1)).bulkAdd(any());
        verifyNoMoreInteractions(offerService);		
	}

	@Test
	public void testAddSingleOfferSuccess() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");
		List<OfferDTO> offer = Arrays.asList(new OfferDTO( "Title", "Description", 1L, 1L, validFromDate, validToDate));
		
		List<OfferDTO> actualOffer = Arrays.asList( new OfferDTO(1L, "TITLE", "Description", 1L, 1L, validFromDate, validToDate));
		when(offerService.bulkAdd(any())).thenReturn(actualOffer);
		
		mockMvc.perform(
                post("/offers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(offer)))
						.andExpect(status().isCreated()).andDo(print());
		verify(offerService, times(1)).bulkAdd(any());
        verifyNoMoreInteractions(offerService);		
	}

	@Test
	public void testAddOfferNotAdded() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");
		List<OfferDTO> offer = Arrays.asList(new OfferDTO( "Title", "Description", 1L, 1L, validFromDate, validToDate));
		when(offerService.bulkAdd(any())).thenReturn(null);
		
		mockMvc.perform(
                post("/offers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(offer)))
						.andExpect(status().isInternalServerError());	
	}

	@Test
	public void testAddOfferRuntimException() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");
		List<OfferDTO> offer = Arrays.asList(new OfferDTO( "Title", "Description", 1L, 1L, validFromDate, validToDate));
		when(offerService.addOffer(any())).thenThrow(new RuntimeException());
		
		mockMvc.perform(
                post("/offers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(offer)))
						.andExpect(status().isInternalServerError());	
	}

	@Test
	public void testFindAllOffersSuccess() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		Date validFromDate = dateformat.parse("2017-01-30");
		Date validToDate = dateformat.parse("2017-02-20");
		List<OfferDTO> lOfAllOffers = Arrays.asList(
				new OfferDTO(1L, "Save 40% at papajohns.com", "To redeem this offer, go to www.papajohns.com, login to your online account and enter code GET40 in the \"Enter a Promo Code\" field. ", 1L, 1L, validFromDate, validToDate),
				new OfferDTO(2L, "2 for 1 Tour", "Book your Tour with us and receive two tickets for the price of one.", 2L, 2L, validFromDate, validToDate));
		
		when(offerService.findAllOffers()).thenReturn(lOfAllOffers);
		
		mockMvc.perform(get("/offers/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].title", is("Save 40% at papajohns.com")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].title", is("2 for 1 Tour")));
		verify(offerService, times(1)).findAllOffers();
		verifyNoMoreInteractions(offerService);
	}

	@Test
	public void testFindAllOffersNoResults() throws Exception {
		when(offerService.findAllOffers()).thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/offers/all"))
				.andExpect(status().isNoContent());
		verify(offerService, times(1)).findAllOffers();
		verifyNoMoreInteractions(offerService);
	}

	@Test
	public void testFindAllOffersNullResult() throws Exception {
		
		when(offerService.findAllOffers()).thenReturn(null);
		
		mockMvc.perform(get("/offers/all"))
				.andExpect(status().isNoContent());
		verify(offerService, times(1)).findAllOffers();
		verifyNoMoreInteractions(offerService);
	}
	
	
    /*
     * converts a Java object into JSON representation
     */
    private static String asJsonString(final Object obj) {
        try {
            String writeValueAsString = new ObjectMapper().writeValueAsString(obj);
            System.out.println(writeValueAsString);
			return writeValueAsString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
