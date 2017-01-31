package com.marketplace.offer.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TOFFER")
public class OfferDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TITLE", nullable=false)
	private String title;
	
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "TYPE_ID", nullable=false)
	private Long typeId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "TYPE_ID", nullable = false, insertable = false, updatable = false)
	private OfferTypeDTO offerType;

	@Column(name = "MERCHANT_ID", nullable=false)
	private Long merchantId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "MERCHANT_ID", nullable = false, insertable = false, updatable = false)
	private MerchantDTO merchantDTO;

	@Column(name = "VALID_FROM")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date validFrom;

	@Column(name = "VALID_TO")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date validTo;

	public OfferDTO() {
	}

	public OfferDTO(String title, String description, Long typeId, Long merchantId, Date validFrom, Date validTo) {
		super();
		this.title = title;
		this.description = description;
		this.typeId = typeId;
		this.merchantId = merchantId;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public OfferDTO(Long id, String title, String description, Long typeId, Long merchantId, Date validFrom,
			Date validTo) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.typeId = typeId;
		this.merchantId = merchantId;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Long getTypeId() {
		return typeId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public OfferTypeDTO getOfferType() {
		return offerType;
	}

	public MerchantDTO getMerchantDTO() {
		return merchantDTO;
	}

	@Override
	public String toString() {
		return "OfferDTO [id=" + id + ", title=" + title + ", description=" + description + ", typeId=" + typeId
				+ ", merchantId=" + merchantId + ", validFrom=" + validFrom + ", validTo=" + validTo + "]";
	}

}
