package com.subscription_processor.core.messaging;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GenerateInstallmentsEventPublisher {

    private final SqsTemplate sqsTemplate;
    private final String queueName;

    public GenerateInstallmentsEventPublisher(
            SqsTemplate sqsTemplate,
            @Value("${app.queues.generate-installments-requested}") String queueName
    ) {
        this.sqsTemplate = sqsTemplate;
        this.queueName = queueName;
    }
}
