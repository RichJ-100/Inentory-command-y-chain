package com.logistpulse.inventory.receiver;

import org.springframework.stereotype.Service;

@Service
public class InventoryReceiver {
    public void doReserve(String sku, int quantity) {
        System.out.println("📦 [RECEIVER] Éxito: Reservando " + quantity + " unidades del SKU " + sku);
    }
}
