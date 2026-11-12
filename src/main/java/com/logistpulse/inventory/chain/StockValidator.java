package com.logistpulse.inventory.chain;

import com.logistpulse.inventory.domain.ReservationRequest;
import org.springframework.stereotype.Component;

@Component
public class StockValidator extends ValidationHandler {
    @Override
    public void validate(ReservationRequest request) {
        if (request.quantity() <= 0) {
            throw new IllegalArgumentException("❌ [CHAIN] Rechazado: Cantidad inválida");
        }
        System.out.println("✅ [CHAIN] Stock suficiente para: " + request.quantity() + " unidades.");
        if (next != null) next.validate(request);
    }
}
