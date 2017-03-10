package com.marketplace;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;

@RunWith(MockitoJUnitRunner.class)
public class ServetInitializerTest {
	
	@InjectMocks
	private ServletInitializer servletInitializer;

	@Mock
	private SpringApplicationBuilder springApplicationBuilder;
	
	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	@Test(expected=AssertionError.class)
	public void test() throws Exception {
		servletInitializer.configure(null);			
	}
	
	@Test
	public void testSuccess() throws Exception {
		when(springApplicationBuilder.sources(any())).thenReturn(springApplicationBuilder);
		SpringApplicationBuilder configure = servletInitializer.configure(springApplicationBuilder);
		assertThat(configure, notNullValue());
	}

}
