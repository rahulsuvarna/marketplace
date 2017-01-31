package com.marketplace.offer.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TOFFTYPE")
public class OfferTypeDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="OFFER_TYPE")
	private String type;

	public OfferTypeDTO() {
	}
	public OfferTypeDTO(long id, String type) {
		this.id = id;
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	@Override
	public String toString() {
		return "OfferTypeDTO [id=" + id + ", type=" + type + "]";
	}
	
	
}
