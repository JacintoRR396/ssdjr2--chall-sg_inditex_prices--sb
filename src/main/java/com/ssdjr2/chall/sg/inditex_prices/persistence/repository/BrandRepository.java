package com.ssdjr2.chall.sg.inditex_prices.persistence.repository;

import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> { }
