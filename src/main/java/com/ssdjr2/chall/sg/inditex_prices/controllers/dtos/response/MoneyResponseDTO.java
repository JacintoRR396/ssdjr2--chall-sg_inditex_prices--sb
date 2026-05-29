package com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.response;

import java.math.BigDecimal;

public record MoneyResponseDTO(BigDecimal price, String currency) { }
