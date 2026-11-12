package com.logistpulse.inventory.api;

import com.logistpulse.inventory.chain.ValidationChainService;
import com.logistpulse.inventory.command.Command;
import com.logistpulse.inventory.command.ReserveStockCommand;
import com.logistpulse.inventory.domain.ReservationRequest;
import com.logistpulse.inventory.receiver.InventoryReceiver;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    
    private final ValidationChainService chain;
    private final InventoryReceiver receiver;

    public ReservationController(ValidationChainService chain, InventoryReceiver receiver) {
        this.chain = chain;
        this.receiver = receiver;
    }

    @PostMapping
    public String createReservation(@RequestBody ReservationRequest request) {
        System.out.println("\n🌐 [INVOKER / API] Petición recibida de: " + request.requestedBy());
        
        // El Invoker instancia y ejecuta el comando
        Command command = new ReserveStockCommand(request, chain, receiver);
        command.execute();
        
        return "Reserva procesada para SKU: " + request.sku();
    }
}
