package com.reca.apiRestFull.service;

import com.reca.apiRestFull.client.OpenExchangeConsumer;
import com.reca.apiRestFull.dto.CurrenciesDTO;
import com.reca.apiRestFull.dto.LatestRatesDTO;
import jakarta.persistence.EntityNotFoundException; // IMPORT ADICIONADO
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ExchangeService {

    private final String API_TOKEN;
    private final OpenExchangeConsumer openExchangeConsumer;

    public ExchangeService(@Value("${API-TOKEN}")  String API_TOKEN, OpenExchangeConsumer openExchangeConsumer) {
        this.API_TOKEN = API_TOKEN;
        this.openExchangeConsumer = openExchangeConsumer;

    }

    public LatestRatesDTO getLatestRates(String symbols, String base){
        if(!"usd".equals(base)){

            LatestRatesDTO ratesDTO = openExchangeConsumer.getLatestRates(this.API_TOKEN,symbols,"usd");
            Map<String, Double> ratesCorrigidas = new HashMap<>();
            String baseUp = base.toUpperCase();
            for(String chave : ratesDTO.rates().keySet()){
                ratesCorrigidas.put(chave,ratesDTO.rates().get(chave)/ratesDTO.rates().get(baseUp));
            }

            return new LatestRatesDTO(ratesCorrigidas,
                    null,
                    null,
                    null,
                    null);

        }
        return openExchangeConsumer.getLatestRates(this.API_TOKEN,symbols,base);
    }

    public Map<String,String> getCurrencies(Boolean show_alternatives){
        return openExchangeConsumer.getOcurrencies(this.API_TOKEN, show_alternatives);
    }

    public Double getRateByCurrencyName(String currencyName) {
        Map<String, String> allCurrencies = this.getCurrencies(false);

        Optional<String> symbolOptional = allCurrencies.entrySet().stream()
                .filter(entry -> entry.getValue().equalsIgnoreCase(currencyName))
                .map(Map.Entry::getKey)
                .findFirst();

        if (symbolOptional.isEmpty()) {
            throw new EntityNotFoundException("Moeda não encontrada: " + currencyName);
        }

        String symbol = symbolOptional.get();

        LatestRatesDTO ratesDTO = this.getLatestRates(symbol, "usd");

        Double rate = ratesDTO.rates().get(symbol);

        if (rate == null) {
            throw new EntityNotFoundException("Cotação não encontrada para o símbolo: " + symbol);
        }
        return rate;
    }
    public Double getRateBySymbol(String symbol) {
        String symbolUpper = symbol.toUpperCase();

        LatestRatesDTO ratesDTO = this.getLatestRates(symbolUpper, "usd");

        Double rate = ratesDTO.rates().get(symbolUpper);

        if (rate == null) {
            throw new EntityNotFoundException("Cotação não encontrada para o símbolo: " + symbolUpper);
        }

        return rate;
    }

    public Double convertToUsd(String code, Double valueToConvert) {
        if (code.equalsIgnoreCase("USD")) {
            return valueToConvert;
        }

        //    Este método já busca a cotação em relação ao USD
        Double rate = this.getRateBySymbol(code);

        if (rate == null || rate == 0) {
            throw new ArithmeticException("Taxa de câmbio inválida ou nula para " + code);
        }

        return valueToConvert / rate;
    }
}