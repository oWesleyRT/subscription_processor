package com.subscription_processor.domain.service;

import com.subscription_processor.core.enums.SubscriptionStatus;
import com.subscription_processor.core.messaging.SubscriptionCreatedEvent;
import com.subscription_processor.domain.SubscriptionRepository;
import com.subscription_processor.domain.model.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;

    @Override
    public void activate(SubscriptionCreatedEvent event) {
        var subscriptionId = event.getSubscriptionId();

        Subscription subscription = repository.findById(event.getSubscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription não encontrada: " + subscriptionId));

        subscription.setStatus(SubscriptionStatus.ACTIVATING);
        repository.save(subscription);

        for(int i=0; i<100000; i++) {
            System.out.print("Contando I");
        }

        subscription.setStatus(SubscriptionStatus.ACTIVE);
        repository.save(subscription);
    }
}
