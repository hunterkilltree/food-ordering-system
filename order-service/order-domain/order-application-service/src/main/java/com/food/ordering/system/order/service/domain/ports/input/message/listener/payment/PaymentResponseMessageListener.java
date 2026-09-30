package com.food.ordering.system.order.service.domain.ports.input.message.listener.payment;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;

// Input port (mirror of DomainEventPublisher, but for things coming in
// rather than going out): declares what the application can be told by
// the payment service, without depending on Kafka/whatever transport
// carries the message. A Kafka consumer adapter in the order-messaging
// module implements this and calls into it once a PaymentResponse message
// is deserialized; that keeps this domain-facing module free of any
// messaging-library dependency.
public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);
}
