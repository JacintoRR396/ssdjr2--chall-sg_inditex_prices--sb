package com.ssdjr2.chall.sg.inditex_prices.repositories;

import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> { }
