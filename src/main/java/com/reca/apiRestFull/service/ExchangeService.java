package com.reca.apiRestFull.service;

import com.reca.apiRestFull.client.OpenExchangeConsumer;
import com.reca.apiRestFull.dto.CurrenciesDTO;
import com.reca.apiRestFull.dto.LatestRatesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;


@Service
public class ExchangeService {

    private final String API_TOKEN;
    private final OpenExchangeConsumer openExchangeConsumer;

    public ExchangeService(@Value("${API-TOKEN}")  String API_TOKEN, OpenExchangeConsumer openExchangeConsumer) {
        this.API_TOKEN = API_TOKEN;
        this.openExchangeConsumer = openExchangeConsumer;

    }

    public LatestRatesDTO getLatestRates(){

        URI uri = UriComponentsBuilder
                .fromUriString("/api/latest.json")
                .queryParam("app_id", this.API_TOKEN)
                .queryParam("symbols","")
                .build()
                .toUri();

        WebClient client = WebClient.builder()
                .baseUrl("https://openexchangerates.org"+uri.toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();


//        System.out.println(uri.toString());

        LatestRatesDTO res = client.get()
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        erro -> {
                            return Mono.error(new Exception("Erro ao processar os dados"));
                        })
                .bodyToMono(LatestRatesDTO.class)
                .block();

        return res;
    }

    public Map<String,String> getCurrencies(Boolean show_alternatives){
        return openExchangeConsumer.getOcurrencies(this.API_TOKEN, show_alternatives);
    }
}
