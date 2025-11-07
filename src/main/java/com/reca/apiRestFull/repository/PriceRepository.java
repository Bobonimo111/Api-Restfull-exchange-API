package com.reca.apiRestFull.repository;

import com.reca.apiRestFull.models.PriceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceModel, Long> {

}
