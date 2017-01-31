package com.marketplace.offer.service;

import java.util.List;

import com.marketplace.offer.dto.OfferDTO;

public interface IOfferService {

	OfferDTO addOffer(OfferDTO anOfferDTO);

	List<OfferDTO> findAllOffers();

	List<OfferDTO> bulkAdd(List<OfferDTO> lOfOfferDTOs);

	List<OfferDTO> findOffersForMerchantId(Long merchantId);

}
