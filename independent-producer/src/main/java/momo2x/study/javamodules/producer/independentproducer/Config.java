package momo2x.study.javamodules.producer.independentproducer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private static final String QUEUE_NAME = "java-modules-study-queue";

    public static final String TOPIC_EXCHANGE_NAME = "java-modules-study-exchange";

    @Bean
    public String routingKey() {
        return "momo2x.study.javamodules.#";
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(
            final String routingKey,
            final Queue queue,
            final TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey);
    }

//    @Bean
//    public SimpleMessageListenerContainer container(
//            final ConnectionFactory connectionFactory,
//            final MessageListenerAdapter listenerAdapter) {
//        final var container = new SimpleMessageListenerContainer();
//
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_NAME);
//        container.setMessageListener(listenerAdapter);
//
//        return container;
//    }

//    @Bean
//    public MessageListenerAdapter listenerAdapter(final Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }


}
