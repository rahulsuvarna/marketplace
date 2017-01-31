package com.marketplace.offer.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TMERCHNT")
public class MerchantDTO {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="CODE")
	private String code;

	@Column(name="NAME")
	private String name;

	@Column(name="CURRENCY_CODE")
	private String currencyCode;
	
	
	public MerchantDTO() {
		super();
	}


	public MerchantDTO(Long id, String code, String name, String currencyCode) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.currencyCode = currencyCode;
	}


	public Long getId() {
		return id;
	}


	public String getCode() {
		return code;
	}


	public String getName() {
		return name;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	@Override
	public String toString() {
		return "MerchantDTO [id=" + id + ", code=" + code + ", name=" + name + ", currencyCode=" + currencyCode + "]";
	}
	
	
}
