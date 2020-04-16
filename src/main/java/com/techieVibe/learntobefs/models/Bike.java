package com.techieVibe.learntobefs.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BIKE")
public class Bike implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2092727739288309047L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;

	@Column(name = "PURCHASE_PRICE")
	private BigDecimal purchasePrice;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	@Column(name = "PURCHASE_DATE")
	private Date purchaseDate;

	@Column(name = "CONTACT")
	private boolean contact;

}
