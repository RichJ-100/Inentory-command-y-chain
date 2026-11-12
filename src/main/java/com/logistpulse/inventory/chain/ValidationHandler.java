package com.logistpulse.inventory.chain;

import com.logistpulse.inventory.domain.ReservationRequest;

public abstract class ValidationHandler {
    protected ValidationHandler next;

    public ValidationHandler setNext(ValidationHandler next) {
        this.next = next;
        return next;
    }

    public abstract void validate(ReservationRequest request);
}
