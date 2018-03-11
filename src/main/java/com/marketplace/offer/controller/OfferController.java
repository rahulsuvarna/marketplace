package com.marketplace.offer.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * using POST method to add a list of offers for a specific merchant and offertype.
	 * URI /offers/add
	 * @param lOfOfferDTOs
	 * @return An empty message body in case of success or failure along with following status code
	 * <pre>
	 * SUCCESS	{@link HttpStatus.CREATED}
	 * Failure	{@link HttpStatus.INTERNAL_SERVER_ERROR}
	 * </pre>
	 */
    @RequestMapping(value = "/merchants/{merchantId}/offers", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<List<OfferDTO>> addOffers(@Valid @RequestBody final List<OfferDTO> lOfOfferDTOs, @PathVariable(name="merchantId", required=true) final Long merchantId) {
		log.debug("Servicing request to add an Offer");
		
		List<OfferDTO> collect = lOfOfferDTOs.stream().filter(offer -> !offer.getMerchantId().equals(merchantId)).collect(Collectors.toList());
		
		if (collect.size() > 0) {
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		final List<OfferDTO> lOfAddedOfferDTOs = offerService.bulkAdd(lOfOfferDTOs);
		if (lOfAddedOfferDTOs == null || lOfAddedOfferDTOs.isEmpty()) {
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.CREATED);
		}
	}
    
    /**
     * using GET to return all available offers
     * URI /offers/find/all
     * @param merchantId
     * @return If offers found then status code HttpStatus.OK along with a list of offers in body
     * if no offers found then a status code of HttpStatus.NO_CONTENT with empty body.
     */
    //@CrossOrigin(origins = "http://127.0.0.1:61289")
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/merchants/{merchantId}/offers", method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<List<OfferDTO>> findOffersForMerchantId(@PathVariable(name="merchantId", required=true) final Long merchantId) {
		log.debug("Servicing request to find all Offers");
		final List<OfferDTO> findAllOffers = offerService.findOffersByMerchantId(merchantId);
		if (findAllOffers == null || findAllOffers.isEmpty()) {
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<OfferDTO>>(findAllOffers, HttpStatus.OK);
		}
	}
    
    /**
     * Provides a list of offers from a specific merchant
     * @param merchantId
     * @return If offers found then status code HttpStatus.OK along with a list of offers in body
     * if no offers found then a status code of HttpStatus.NO_CONTENT with empty body.
     */
    @RequestMapping(value = {"/merchants/{merchantId}/offers/{offerId}"}, method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<List<OfferDTO>> findMerchantOffersByOfferId( @PathVariable(name="merchantId", required=true) final Long merchantId,
			@PathVariable(name="offerId", required=true) final Long offerId) {
		log.debug("Servicing request to find Offers from a Merchant");
		final List<OfferDTO> findAllOffers = offerService.findMerchantOffersByOfferId(merchantId, offerId);
		if (findAllOffers == null || findAllOffers.isEmpty()) {
			return new ResponseEntity<List<OfferDTO>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<OfferDTO>>(findAllOffers, HttpStatus.OK);
		}
	}
    
	
}
