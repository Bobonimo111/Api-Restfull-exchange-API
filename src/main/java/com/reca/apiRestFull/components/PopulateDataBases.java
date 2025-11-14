package com.reca.apiRestFull.components;

import com.reca.apiRestFull.client.OpenExchangeConsumer;
import com.reca.apiRestFull.dto.LatestRatesDTO;
import com.reca.apiRestFull.models.CurrencieModel;
import com.reca.apiRestFull.models.PriceModel;
import com.reca.apiRestFull.repository.CurrencieRepository;
import com.reca.apiRestFull.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class PopulateDataBases implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        this.populate();
    }

    private final OpenExchangeConsumer openExchangeConsumer;
    private final CurrencieRepository currencieRepository;
    private  final String API_TOKEN;
    private  final PriceRepository priceRepository;


    public PopulateDataBases(OpenExchangeConsumer openExchangeConsumer,
                             CurrencieRepository currencieRepository,
                             @Value("${API-TOKEN}")  String apiToken,
                             PriceRepository priceRepository) {
        this.openExchangeConsumer = openExchangeConsumer;
        this.currencieRepository = currencieRepository;
        API_TOKEN = apiToken;
        this.priceRepository = priceRepository;
    }

    public void populate(){
        Map<String,String> currenciesMap = openExchangeConsumer.getCurrencies(this.API_TOKEN,true);
        LocalDateTime nowDateTime = LocalDateTime.now();
        for(String key : currenciesMap.keySet()){
            CurrencieModel currencyModel = CurrencieModel.builder()
                    .createdAt(nowDateTime)
                    .symbol(key)
                    .name(currenciesMap.get(key))
                    .build();
            currencieRepository.save(currencyModel);
        }

        LatestRatesDTO latestRatesDTO = openExchangeConsumer.getLatestRates(this.API_TOKEN,"","USD");

        for(String key : latestRatesDTO.rates().keySet()){
            PriceModel priceModel = PriceModel.builder()
                    .base("USD")
                    .value(latestRatesDTO.rates().get(key))
                    .createdAt(nowDateTime)
                    .currencie(currencieRepository.findBySymbol(key))
                    .build();
            priceRepository.save(priceModel);
        }

    }
}
