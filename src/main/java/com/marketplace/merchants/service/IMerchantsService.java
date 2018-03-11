package com.marketplace.merchants.service;

import java.util.List;

import com.marketplace.offer.dto.MerchantDTO;

public interface IMerchantsService {
    public MerchantDTO addMerchant(MerchantDTO merchantDTO);
    public List<MerchantDTO> getAllMerchants();
    public void deleteMerchant(Long id);
    public boolean findMerchant(Long id);
	public MerchantDTO getMerchant(Long id);
}