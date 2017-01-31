package com.marketplace.offer.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.service.IOfferService;
/**
 * The Offer Controller responsible for servicing all offer requests
 * @author 
 *
 */
@RestController
public class OfferController {
	
	private static final Logger log = LoggerFactory.getLogger(OfferController.class);

	@Autowired
	private IOfferService offerService;    
	
	/**
	 * using POST method to add a list of offers.
	 * @param lOfOfferDTOs
	 * @return An empty message body in case of success or failure 
	 * <pre>
	 * SUCCESS	{@link HttpStatus.CREATED}
	 * Failure	{@link HttpStatus.INTERNAL_SERVER_ERROR}
	 * </pre>
	 */
    @RequestMapping(value = "/offers/add", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<List<OfferDTO>> addOffers(@Valid @RequestBody List<OfferDTO> lOfOfferDTOs) {
		log.debug("Servicing request to add an Offer");
		List<OfferDTO> lOfAddedOfferDTOs = offerService.bulkAdd(lOfOfferDTOs);
		if (lOfAddedOfferDTOs == null || lOfAddedOfferDTOs.isEmpty()) {
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.CREATED);
		}
	}
    
    /**
     * using GET to return all available offers
     * @return a List of Offers or an empty body
     * <pre>
	 * SUCCESS		{@link HttpStatus.OK}
	 * NOT FOUND	{@link HttpStatus.NO_CONTENT}
     * </pre>
     */
    @RequestMapping(value = "/offers/all", method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<List<OfferDTO>> findAll() {
		log.debug("Servicing request to find all Offers");
		List<OfferDTO> findAllOffers = offerService.findAllOffers();
		if (findAllOffers == null || findAllOffers.isEmpty()) {
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<OfferDTO>>(findAllOffers, HttpStatus.OK);
		}
	}
	
}
