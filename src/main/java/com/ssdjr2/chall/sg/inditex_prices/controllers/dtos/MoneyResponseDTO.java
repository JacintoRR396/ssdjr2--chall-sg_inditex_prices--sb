package com.ssdjr2.chall.sg.inditex_prices.controllers.dtos;

import java.math.BigDecimal;

public record MoneyResponseDTO(BigDecimal price, String currency) { }
