package com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.entity;

import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.common.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(schema = "ECOMMERCE", name = "PRICES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceEntity extends AuditableEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 5622094071967246451L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRICE_ID", unique = true, nullable = false, updatable = false)
	private Long id;

	@Column(name = "BRAND_ID", nullable = false)
	private Long brandId;

	@Column(name = "PRODUCT_ID", nullable = false)
	private Integer productId;

	@Column(name = "PRICE_LIST", nullable = false)
	private Integer priceList;

	@Column(name = "PRIORITY", nullable = false)
	private Integer priority;

	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "END_DATE", nullable = false)
	private LocalDateTime endDate;

	@Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	@Column(name = "CURRENCY", nullable = false, length = 3)
	private String currency;
}
