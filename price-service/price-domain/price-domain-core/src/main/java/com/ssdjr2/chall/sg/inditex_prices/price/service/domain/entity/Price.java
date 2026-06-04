package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity;

import com.ssdjr2.chall.sg.inditex_prices.domain.entity.AggregateRoot;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.*;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.BrandId;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.PriceId;
import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.ProductId;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception.PriceDomainException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class Price extends AggregateRoot<PriceId> {
	private final BrandId brandId;
	private final ProductId productId;
	private final Integer priceList;
	private final Integer priority;
	private final ApplicationDates applicationDates;
	private final Money money;

	public void initPrice(){
		this.validatePrice();
	}

	private void validatePrice() {
		if (Objects.isNull(brandId)) {
			throw new PriceDomainException("error.brand_id.null");
		}
		this.checkNumberGreaterThanZero(brandId.getId());
		if (Objects.isNull(productId)) {
			throw new PriceDomainException("error.product_id.null");
		}
		this.checkNumberGreaterThanZero(productId.getId());
		this.checkNumberGreaterThanZero(priceList);
		this.checkNumberGreaterThanZero(priority);
	}

	private void checkNumberGreaterThanZero(Number value){
		if( Objects.isNull(value) ) {
			throw new PriceDomainException("error.price.number.null");
		}

		if( value.doubleValue() <= 0){
			throw new PriceDomainException("error.price.number.invalid_range");
		}
	}
}
