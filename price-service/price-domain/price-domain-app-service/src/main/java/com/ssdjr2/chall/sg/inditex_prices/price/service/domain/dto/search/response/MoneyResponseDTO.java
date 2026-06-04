package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response;

import java.math.BigDecimal;

public record MoneyResponseDTO(BigDecimal price, String currency) { }
