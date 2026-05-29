package com.ssdjr2.chall.sg.inditex_prices.domain.dtos;

public record PriceSearchResponseDTO(Integer brandId, Integer productId, Integer priceList,
                                     ApplicationDatesResponseDTO applicationDates, MoneyResponseDTO money) { }
