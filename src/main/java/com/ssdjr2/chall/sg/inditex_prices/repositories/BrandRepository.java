package com.ssdjr2.chall.sg.inditex_prices.repositories;

import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> { }
