package com.subscription_processor.core.messaging;

import com.subscription_processor.core.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SubscriptionGenerateInstallmentsEvent {

    private UUID subscriptionId;

    private UUID customerId;

    private UUID planId;

    private PaymentMethod paymentMethod;

}
