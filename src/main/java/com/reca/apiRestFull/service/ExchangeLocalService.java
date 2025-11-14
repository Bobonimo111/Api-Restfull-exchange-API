package com.reca.apiRestFull.service;

import com.reca.apiRestFull.dto.LatestRatesDTO;
import com.reca.apiRestFull.models.CurrencieModel;
import com.reca.apiRestFull.models.PriceModel;
import com.reca.apiRestFull.repository.CurrencieRepository;
import com.reca.apiRestFull.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExchangeLocalService {
    private final CurrencieRepository currencieRepository;
    private final PriceRepository priceRepository;

    public ExchangeLocalService(CurrencieRepository currencieRepository, PriceRepository priceRepository) {
        this.currencieRepository = currencieRepository;
        this.priceRepository = priceRepository;
    }

    //Emular um retorno da api consultando localmente as moedas
    public Map<String,String> getOcurrencies(Boolean showAlternative) {
        List<CurrencieModel> currencie = currencieRepository.findAll();
        Map<String,String> retorno = new HashMap<>();
        for(CurrencieModel currency : currencie){
            if(showAlternative){
                retorno.put(currency.getSymbol(), currency.getName());
            }else if(currency.getSymbol().length() < 4){
                retorno.put(currency.getSymbol(), currency.getName());
            }
        }
        return retorno;
    }

    //Emular um retorno da api consultando localmente as cotações
    public LatestRatesDTO getLatestRates(String symbols, String base) {
        List<PriceModel> priceModelList = priceRepository.findAll();
        Map<String,Double> priceMap = priceModelList
                .stream()
                .collect(
                        Collectors.toMap(
                                t -> t.getCurrencie().getSymbol(), PriceModel::getValue));
        LatestRatesDTO latestRatesDTO = new LatestRatesDTO(priceMap ,
                null,null,null,null,false);
        return latestRatesDTO;
    }
}
