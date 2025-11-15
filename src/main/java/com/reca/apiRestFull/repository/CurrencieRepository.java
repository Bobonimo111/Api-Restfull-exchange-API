package com.reca.apiRestFull.repository;

import com.reca.apiRestFull.models.CurrencieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencieRepository extends JpaRepository<CurrencieModel, Long> {

    Optional<CurrencieModel> findBySymbol(String symbol);

    void removeBySymbol(String symbol);
}
