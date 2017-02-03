package com.marketplace.offer.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.config.ResourceCreated;
import com.marketplace.offer.dto.OfferDTO;

@RestController
public class PutPostController {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	private static AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/foos", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody final List<OfferDTO> lOfOfferDTOs, HttpServletResponse response) {
		long andIncrement = counter.getAndIncrement();
		eventPublisher.publishEvent(new ResourceCreated(this, response, andIncrement));
	}
}