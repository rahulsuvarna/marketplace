package com.marketplace.merchants.service;

import com.marketplace.merchants.repository.MerchantsRepository;
import com.marketplace.offer.dto.MerchantDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantsServiceImpl implements IMerchantsService {

    @Autowired
    private MerchantsRepository merchantsRepository;

	@Override
	public MerchantDTO addMerchant(MerchantDTO merchantDTO) {
		return merchantsRepository.save(merchantDTO);
	}

	@Override
	public List<MerchantDTO> getAllMerchants() {
		return merchantsRepository.findAll();
	}

	@Override
	public void deleteMerchant(Long id) {
		merchantsRepository.delete(id);
	}

	@Override
	public boolean findMerchant(Long id) {
		return merchantsRepository.exists(id);
	}

	@Override
	public MerchantDTO getMerchant(Long id) {
		return merchantsRepository.findOne(id); 
	}

}