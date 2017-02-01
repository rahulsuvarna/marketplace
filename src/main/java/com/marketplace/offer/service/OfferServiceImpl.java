package com.marketplace.offer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marketplace.offer.dto.OfferDTO;
import com.marketplace.offer.repository.OfferRepository;

@Component
public class OfferServiceImpl implements IOfferService {

	@Autowired
	private OfferRepository offerRepository;
	
	/**
	 * Service method to add a single Offer
	 */
	@Override
	public OfferDTO addOffer(final OfferDTO anOfferDTO) {
		assert anOfferDTO != null;
		return offerRepository.save(anOfferDTO);
	}

	/**
	 * Service method to find a list of Offers
	 */
	@Override
	public List<OfferDTO> findAllOffers() {
		return offerRepository.findAll();
	}

	/**
	 * 
	 * Service method to add a list of Offers
	 */
	@Override
	public List<OfferDTO> bulkAdd(List<OfferDTO> lOfOfferDTOs) {		
		return offerRepository.save(lOfOfferDTOs);
	}

	@Override
	public List<OfferDTO> findOffersForMerchantId(final Long merchantId) {
		// TODO Auto-generated method stub
		return offerRepository.findByMerchantId(merchantId);
	}

}
