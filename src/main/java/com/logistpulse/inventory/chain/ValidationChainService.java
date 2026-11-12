package com.logistpulse.inventory.chain;

import com.logistpulse.inventory.domain.ReservationRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ValidationChainService {
    private final SkuValidator skuValidator;
    private final StockValidator stockValidator;
    private ValidationHandler chainStart;

    public ValidationChainService(SkuValidator skuValidator, StockValidator stockValidator) {
        this.skuValidator = skuValidator;
        this.stockValidator = stockValidator;
    }

    @PostConstruct
    public void init() {
        // Armamos la cadena en orden: Primero SKU, luego Stock
        this.chainStart = skuValidator;
        skuValidator.setNext(stockValidator);
    }

    public void validate(ReservationRequest request) {
        chainStart.validate(request);
    }
}
