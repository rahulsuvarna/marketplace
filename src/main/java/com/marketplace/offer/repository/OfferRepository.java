package com.marketplace.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.offer.dto.OfferDTO;

public interface OfferRepository extends JpaRepository<OfferDTO, Long> {

}
