package com.marketplace.offer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.offer.dto.OfferDTO;

public interface OfferRepository extends JpaRepository<OfferDTO, Long> {

	List<OfferDTO> findByMerchantId(Long merchantId);

}
