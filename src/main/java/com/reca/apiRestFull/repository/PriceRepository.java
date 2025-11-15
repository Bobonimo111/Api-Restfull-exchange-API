package com.reca.apiRestFull.repository;

import com.reca.apiRestFull.models.PriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceModel, Long> {

    List<PriceModel> findAllByCurrencieSymbol(String symbol);
}
