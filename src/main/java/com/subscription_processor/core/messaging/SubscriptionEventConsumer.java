package com.subscription_processor.core.messaging;

import com.subscription_processor.domain.service.SubscriptionService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionEventConsumer {

    private final SubscriptionService service;

    @SqsListener("${app.queues.subscription-created}")
    public void onSubscriptionCreated(SubscriptionCreatedEvent event) {
        log.debug("Processando subscriptionId={}", event.getSubscriptionId());
        service.activate(event);
        log.debug("Mensagem processada. subscriptionId={}", event.getSubscriptionId());
    }
}