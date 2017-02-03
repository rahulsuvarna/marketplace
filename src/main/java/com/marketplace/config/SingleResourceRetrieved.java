package com.marketplace.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class SingleResourceRetrieved extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;

	public SingleResourceRetrieved(Object source, HttpServletResponse response) {
		super(source);
		this.response = response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
}