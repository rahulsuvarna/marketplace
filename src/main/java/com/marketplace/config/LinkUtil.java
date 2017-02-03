package com.marketplace.config;

final class LinkUtil {
	public static String createLinkHeader(final String uri, final String rel) {
		return "<" + uri + ">; rel=\"" + rel + "\"";
	}
}