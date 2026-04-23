package com.subscription_processor.domain.model;

import com.subscription_processor.core.enums.PaymentMethod;
import com.subscription_processor.core.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Subscription {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private UUID customerId;

    private UUID planId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    private LocalDateTime createdAt;
}
