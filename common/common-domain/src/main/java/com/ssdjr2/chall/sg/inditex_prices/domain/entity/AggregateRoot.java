package com.ssdjr2.chall.sg.inditex_prices.domain.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {}
