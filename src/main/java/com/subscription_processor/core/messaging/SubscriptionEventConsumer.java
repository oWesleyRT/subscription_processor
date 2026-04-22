package com.subscription_processor.core.messaging;

import com.subscription_processor.domain.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import tools.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Component
public class SubscriptionEventConsumer {

    private final SqsAsyncClient sqsAsyncClient;
    private final ObjectMapper objectMapper;
    private final String subscriptionCreatedQueueUrl;
    private final SubscriptionService service;

    public SubscriptionEventConsumer(
            SqsAsyncClient sqsAsyncClient,
            ObjectMapper objectMapper,
            @Value("${app.queues.subscription-created}")
            String queueName,
            SubscriptionService service
    ) {
        this.sqsAsyncClient = sqsAsyncClient;
        this.objectMapper = objectMapper;
        this.subscriptionCreatedQueueUrl = resolveQueueUrl(queueName);
        this.service = service;
    }

    @Scheduled(fixedDelay = 3000)
    public void pollMessages() {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(subscriptionCreatedQueueUrl)
                .maxNumberOfMessages(5)
                .waitTimeSeconds(10)
                .build();

        List<Message> messages = sqsAsyncClient.receiveMessage(request)
                .join()
                .messages();

        if (messages.isEmpty()) {
            return;
        }

        for (Message message : messages) {
            try {
                log.info("Mensagem recebida do SQS. messageId={}", message.messageId());

                SubscriptionCreatedEvent event =
                        objectMapper.readValue(message.body(), SubscriptionCreatedEvent.class);

                log.info("Processando subscriptionId={}", event.getSubscriptionId());

                service.activate(event);

                sqsAsyncClient.deleteMessage(r -> r
                                .queueUrl(subscriptionCreatedQueueUrl)
                                .receiptHandle(message.receiptHandle()))
                        .join();

                log.info("Mensagem removida da fila. subscriptionId={}", event.getSubscriptionId());

            } catch (Exception e) {
                log.error("Erro ao processar mensagem do SQS. body={}", message.body(), e);
            }
        }
    }

    private String resolveQueueUrl(String queueName) {
        return sqsAsyncClient.getQueueUrl(r -> r.queueName(queueName)).join().queueUrl();
    }

}
