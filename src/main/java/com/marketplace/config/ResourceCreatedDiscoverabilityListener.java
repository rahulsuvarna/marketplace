package com.marketplace.config;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.base.Preconditions;

@Component
public class ResourceCreatedDiscoverabilityListener implements ApplicationListener<ResourceCreated> {
	@Override
	public void onApplicationEvent(ResourceCreated resourceCreatedEvent) {
		Preconditions.checkNotNull(resourceCreatedEvent);
		HttpServletResponse response = resourceCreatedEvent.getResponse();
		long idOfNewResource = resourceCreatedEvent.getIdOfNewResource();
		addLinkHeaderOnResourceCreation(response, idOfNewResource);
	}

	private void addLinkHeaderOnResourceCreation(HttpServletResponse response, long idOfNewResource) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idOfNewResource}")
				.buildAndExpand(idOfNewResource).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}