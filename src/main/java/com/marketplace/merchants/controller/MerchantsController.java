package com.marketplace.merchants.controller;

import java.util.List;

import javax.validation.Valid;

import com.marketplace.merchants.service.IMerchantsService;
import com.marketplace.offer.dto.MerchantDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantsController {

    @Autowired
    private IMerchantsService merchantsService;

    @RequestMapping(value="/merchants", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MerchantDTO> addMerchants(@Valid @RequestBody final MerchantDTO merchantDTO) {
        return new ResponseEntity<MerchantDTO>(merchantsService.addMerchant(merchantDTO), HttpStatus.OK);
    }

    @RequestMapping(value="/merchants", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<MerchantDTO>> getAllMerchants() {
        return new ResponseEntity<List<MerchantDTO>>(merchantsService.getAllMerchants(), HttpStatus.OK);
    }

    @RequestMapping(value="/merchants/{id}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MerchantDTO> getMerchant(@Valid @PathVariable(name="merchantId", required=true) final Long id) {
        MerchantDTO merchantDTO = merchantsService.getMerchant(id);
        return new ResponseEntity<MerchantDTO>(merchantDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/merchants/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<MerchantDTO> deleteMerchant(@Valid @PathVariable(name="merchantId", required=true) final Long id) {
        boolean exists = merchantsService.findMerchant(id);
        if (!exists) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        merchantsService.deleteMerchant(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}