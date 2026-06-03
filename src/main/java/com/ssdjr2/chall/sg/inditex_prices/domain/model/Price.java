package com.ssdjr2.chall.sg.inditex_prices.domain.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Price {
	private Long id;
	private Long brandId;
	private Integer productId;
	private Integer priceList;
	private Integer priority;
	private ApplicationDates applicationDates;
	private Money money;
}
