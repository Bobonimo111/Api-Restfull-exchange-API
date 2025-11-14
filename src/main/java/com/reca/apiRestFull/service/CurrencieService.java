package com.reca.apiRestFull.service;

import com.reca.apiRestFull.dto.CurrencieDTO;
import com.reca.apiRestFull.dto.CurrenciesDTO;
import com.reca.apiRestFull.models.CurrencieModel;
import com.reca.apiRestFull.repository.CurrencieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencieService {

    private final CurrencieRepository currencieRepository;

    public CurrencieService(CurrencieRepository currencieRepository) {
        this.currencieRepository = currencieRepository;
    }

    public void CreateNewCurrencie(CurrencieDTO dto){
        CurrencieModel model = CurrencieModel.builder()
                .createdAt(LocalDateTime.now())
                .name(dto.name())
                .symbol(dto.symbol())
                .build();

        currencieRepository.save(model);
    }

    public void removeCurrencie(Long id){
        currencieRepository.deleteById(id);
    }

    public CurrenciesDTO getAllCurrencies(){
        List<CurrencieModel> currencies = currencieRepository.findAll();
        return new CurrenciesDTO(
                currencies
                        .stream()
                        .collect(Collectors.toMap(CurrencieModel::getSymbol,CurrencieModel::getName)),
                true);
    }

    public void putCurrencie(CurrenciesDTO dto){

    }

    public CurrencieDTO getBySymbol(String symbol) {
        CurrencieModel model = currencieRepository.findBySymbol(symbol);
        return new CurrencieDTO(model.getSymbol(), model.getName());
    }

    public void removeCurrencieBySymbol(String symbol) {
        CurrencieModel model = currencieRepository.findBySymbol(symbol);
        currencieRepository.delete(model);
    }
}
