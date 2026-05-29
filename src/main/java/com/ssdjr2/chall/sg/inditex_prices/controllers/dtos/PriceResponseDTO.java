package com.ssdjr2.chall.sg.inditex_prices.controllers.dtos;

public record PriceResponseDTO(Integer brandId, Integer productId, Integer priceList,
                               ApplicationDatesResponseDTO applicationDates, MoneyResponseDTO money) { }
