package com.reca.apiRestFull.repository;

import com.reca.apiRestFull.models.CurrencieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencieRepository extends JpaRepository<CurrencieModel, Long> {

    CurrencieModel findBySymbol(String symbol);

    void removeBySymbol(String symbol);
}
