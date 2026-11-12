package com.logistpulse.inventory.command;

import com.logistpulse.inventory.chain.ValidationChainService;
import com.logistpulse.inventory.domain.ReservationRequest;
import com.logistpulse.inventory.receiver.InventoryReceiver;

public class ReserveStockCommand implements Command {
    private final ReservationRequest request;
    private final ValidationChainService chain;
    private final InventoryReceiver receiver;

    public ReserveStockCommand(ReservationRequest request, ValidationChainService chain, InventoryReceiver receiver) {
        this.request = request;
        this.chain = chain;
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println("\n--- INICIANDO COMMAND: POST /reservations ---");
        
        // 1. Pasa por la cadena de validación
        chain.validate(request);

        // 2. Si pasa, el Receiver hace la reserva
        receiver.doReserve(request.sku(), request.quantity());
        
        System.out.println("--- COMANDO EJECUTADO ---");
    }
}
