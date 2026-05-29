package com.ssdjr2.chall.sg.inditex_prices.domain.dtos;

import java.math.BigDecimal;

public record MoneyResponseDTO(BigDecimal price, String currency) { }
