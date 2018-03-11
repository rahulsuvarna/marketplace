package com.marketplace.merchants.repository;

import com.marketplace.offer.dto.MerchantDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantsRepository extends JpaRepository<MerchantDTO, Long> {
    
}