package com.subscription_processor.domain.service;

import com.subscription_processor.core.messaging.SubscriptionCreatedEvent;

public interface SubscriptionService {

    void activate(SubscriptionCreatedEvent event);

}
