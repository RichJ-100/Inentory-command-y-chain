package com.logistpulse.inventory.domain;

public record ReservationRequest(String sku, int quantity, String requestedBy) {}
