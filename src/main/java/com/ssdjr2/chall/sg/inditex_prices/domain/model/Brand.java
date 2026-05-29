package com.ssdjr2.chall.sg.inditex_prices.domain.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Brand {
	private Long id;
	private String name;
}
