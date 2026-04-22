package com.subscription_processor.core.messaging;

import com.subscription_processor.core.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCreatedEvent {

    private UUID subscriptionId;

    private UUID customerId;

    private UUID planId;

    private String paymentMethod;

    private SubscriptionStatus status;

    private LocalDateTime createdAt;
}
