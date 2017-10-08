package com.marketplace.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Generic Exception Handler will intercept all errors and exception and return a friendly message
 * 
 *
 */
@ControllerAdvice
@RestController
@EnableSwagger2
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		//log.error("Exception occured : ", ex);
		StandardAPIError apiError = new StandardAPIError(HttpStatus.INTERNAL_SERVER_ERROR,
				new Date() ,"There was an error while processing the request", "Please contact Administrator");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
