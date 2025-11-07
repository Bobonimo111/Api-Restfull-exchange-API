package com.reca.apiRestFull.repository;

import com.reca.apiRestFull.models.CurrencieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencieRepository extends JpaRepository<CurrencieModel, Long> {

}
