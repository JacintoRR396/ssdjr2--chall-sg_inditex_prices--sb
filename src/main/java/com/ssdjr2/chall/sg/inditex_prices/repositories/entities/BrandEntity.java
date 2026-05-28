package com.ssdjr2.chall.sg.inditex_prices.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(schema = "ECOMMERCE", name = "BRANDS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity extends AuditableEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = -2022483706143543550L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BRAND_ID", unique = true, nullable = false, updatable = false)
	private Long id;

	@Column(name = "BRAND_NAME", nullable = false)
	private String name;
}
