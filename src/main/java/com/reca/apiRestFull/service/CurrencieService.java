package com.reca.apiRestFull.service;

import com.reca.apiRestFull.dto.CurrencieDTO;
import com.reca.apiRestFull.dto.CurrenciesDTO;
import com.reca.apiRestFull.models.CurrencieModel;
import com.reca.apiRestFull.models.PriceModel;
import com.reca.apiRestFull.repository.CurrencieRepository;
import com.reca.apiRestFull.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencieService {

    private final CurrencieRepository currencieRepository;
    private final PriceRepository priceRepository;

    public CurrencieService(CurrencieRepository currencieRepository,PriceRepository priceRepository) {
        this.currencieRepository = currencieRepository;
        this.priceRepository = priceRepository;
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

    public void updateCurrencie(Long id,CurrencieDTO dto){
        CurrencieModel model = currencieRepository.findById(id).orElseThrow(
                () -> new RuntimeException("")
        );

        model.setName(dto.name());
        model.setSymbol(dto.symbol());

        currencieRepository.save(model);
    }

    public CurrencieDTO getBySymbol(String symbol) {
        CurrencieModel model = currencieRepository.findBySymbol(symbol).orElseThrow(
                ()->new RuntimeException("")
        );
        return new CurrencieDTO(model.getSymbol(), model.getName());
    }

    public void removeCurrencieBySymbol(String symbol) {
        List<PriceModel> priceModelList = priceRepository.findAllByCurrencieSymbol(symbol);
        priceRepository.deleteAll(priceModelList);
        CurrencieModel model = currencieRepository.findBySymbol(symbol).orElseThrow(
                ()-> new RuntimeException(""));
        currencieRepository.delete(model);
    }
}
