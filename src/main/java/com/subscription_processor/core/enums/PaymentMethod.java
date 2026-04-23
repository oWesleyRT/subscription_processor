package com.subscription_processor.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {

    PIX,
    CREDIT_CARD,
    BANK_SLIP

}