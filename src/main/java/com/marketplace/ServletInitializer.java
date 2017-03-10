package com.marketplace;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//TODO commented out because java compiler creates additional bytecode and jacoco should ideally be filtering out.
		//assert null != application;
		return application.sources(MarketPlaceApplication.class);
	}

}
