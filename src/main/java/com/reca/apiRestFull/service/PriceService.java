package com.reca.apiRestFull.service;

import com.reca.apiRestFull.dto.PriceDTO;
import com.reca.apiRestFull.models.CurrencieModel;
import com.reca.apiRestFull.models.PriceModel;
import com.reca.apiRestFull.repository.CurrencieRepository;
import com.reca.apiRestFull.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceService {
    private final CurrencieRepository currencieRepository;
    private final PriceRepository priceRepository;

    public PriceService(CurrencieRepository currencieRepository, PriceRepository priceRepository) {
        this.currencieRepository = currencieRepository;
        this.priceRepository = priceRepository;
    }

    public void createPrice(PriceDTO dto){
        CurrencieModel currencieModel = currencieRepository.findBySymbol(dto.currencie())
                .orElseThrow(()-> new RuntimeException(""));

        PriceModel PriceModel = new PriceModel().builder()
                .base(dto.base())
                .currencie(currencieModel)
                .createdAt(LocalDateTime.now())
                .value(dto.value())
                .build();

        priceRepository.save(PriceModel);
    }

    public PriceDTO getPriceById(Long id){
        PriceModel priceModel = priceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(""));

        return new PriceDTO(priceModel.getValue(),priceModel.getCurrencie().getSymbol(),priceModel.getBase());
    }

    public List<PriceDTO> getAllPricesBySymbol(String symbol){
        List<PriceModel> priceModelList = priceRepository.findAll();

        return priceModelList.stream().map(
                        p ->  new PriceDTO(p.getValue(),p.getCurrencie().getSymbol(),p.getBase())
                )
                .collect(Collectors.toList());
    }

    public void putPrice(Long id, PriceDTO dto){
        PriceModel priceModel = priceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(""));
        priceModel.setBase(dto.base());
        priceModel.setValue(dto.value());
        priceRepository.save(priceModel);
    }

    public void removePrice(Long id){
        priceRepository.deleteById(id);
    }




}
