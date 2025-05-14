package momo2x.study.javamodules.producer.independentproducer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private static final String QUEUE_NAME_1 = "java-modules-study-queue-1";
    private static final String QUEUE_NAME_2 = "java-modules-study-queue-2";

    public static final String TOPIC_EXCHANGE_NAME = "java-modules-study-exchange";

    public static final String ROUTING_KEY_1 = "momo2x.study.javamodules.1.#";
    public static final String ROUTING_KEY_2 = "momo2x.study.javamodules.2.#";

    @Bean(QUEUE_NAME_1)
    public Queue queue1() {
        return new Queue(QUEUE_NAME_1, true);
    }

    @Bean(QUEUE_NAME_2)
    public Queue queue2() {
        return new Queue(QUEUE_NAME_2, true);
    }

    @Bean(TOPIC_EXCHANGE_NAME)
    public TopicExchange exchange2() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean(ROUTING_KEY_1)
    public Binding binding1(
            @Qualifier(QUEUE_NAME_1) final Queue queue,
            final TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_1);
    }

    @Bean(ROUTING_KEY_2)
    public Binding binding2(
            @Qualifier(QUEUE_NAME_2) final Queue queue,
            final TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_2);
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
