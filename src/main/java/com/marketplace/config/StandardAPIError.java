package com.marketplace.config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * API Error class is returned when an error condition is reached.
 * @author 
 *
 */
public class StandardAPIError {
	private HttpStatus status;
	private String message;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date date;
	private List<String> errors;

	public StandardAPIError() {
		super();
	}

	public StandardAPIError(HttpStatus status, Date date, String message, String error) {
		super();
		this.status = status;
		this.date = date;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public Date getDate() {
		return date;
	}
	
	
}
