package com.logistpulse.inventory.chain;

import com.logistpulse.inventory.domain.ReservationRequest;
import org.springframework.stereotype.Component;

@Component
public class SkuValidator extends ValidationHandler {
    @Override
    public void validate(ReservationRequest request) {
        if (request.sku() == null || request.sku().isEmpty()) {
            throw new IllegalArgumentException("❌ [CHAIN] Rechazado: SKU inválido");
        }
        System.out.println("✅ [CHAIN] SKU válido: " + request.sku());
        if (next != null) next.validate(request);
    }
}
