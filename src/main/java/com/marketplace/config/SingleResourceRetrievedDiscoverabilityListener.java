package com.marketplace.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.base.Preconditions;

@Component
public class SingleResourceRetrievedDiscoverabilityListener implements ApplicationListener<SingleResourceRetrieved> {

	@Override
	public void onApplicationEvent(SingleResourceRetrieved resourceRetrievedEvent) {
		Preconditions.checkNotNull(resourceRetrievedEvent);

		HttpServletResponse response = resourceRetrievedEvent.getResponse();
		addLinkHeaderOnSingleResourceRetrieval(response);
	}

	void addLinkHeaderOnSingleResourceRetrieval(HttpServletResponse response) {
		String requestURL = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri().toASCIIString();
		int positionOfLastSlash = requestURL.lastIndexOf("/");
		String uriForResourceCreation = requestURL.substring(0, positionOfLastSlash);

		String linkHeaderValue = LinkUtil.createLinkHeader(uriForResourceCreation, "collection");
		//response.addHeader(LINK_HEADER, linkHeaderValue);
	}
}