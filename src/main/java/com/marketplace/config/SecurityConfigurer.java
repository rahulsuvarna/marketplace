package com.marketplace.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfigurer {

/*extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/merchants/**").authenticated()
			.antMatchers(HttpMethod.DELETE, "/merchants/**").authenticated()
			.antMatchers(HttpMethod.PUT, "/merchants/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.httpBasic().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
	}*/	
}
