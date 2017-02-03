package com.marketplace.offer.service;

import java.util.List;

import com.marketplace.offer.dto.OfferDTO;

public interface IOfferService {

	OfferDTO addOffer(OfferDTO anOfferDTO);

	List<OfferDTO> findOffersByMerchantId(Long merchantId);

	List<OfferDTO> bulkAdd(List<OfferDTO> lOfOfferDTOs);

	List<OfferDTO> findMerchantOffersByOfferId(Long merchantId, Long offerId);

}
