package momo2x.study.javamodules.oldstack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import momo2x.study.javamodules.oldstack.api.OldStackConsumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Config {

    public static final String HOST = "localhost";

    public static final String EXCHANGE_NAME = "java-modules-study-exchange";
    public static final String EXCHANGE_TYPE = "topic";

    private static final String QUEUE_NAME = "java-modules-study-queue-1";

    private Connection connection;
    private Channel channel;

    private boolean initialized;

    public Config init() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST);
            connection = factory.newConnection();

            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, new HashMap<>(0));

            initialized = true;

            return this;

        } catch (final IOException | TimeoutException e) {
            throw new OldStackException(e);
        }

    }

    public Config addConsumer(final OldStackConsumer consumer) {
        if (!initialized) {
            throw new OldStackException("Config must be initialized");
        }

        try {
            channel.basicConsume(
                    QUEUE_NAME,
                    false,
                    new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(
                                String consumerTag,
                                Envelope envelope,
                                AMQP.BasicProperties properties,
                                byte[] body) throws IOException {
                            consumer.consume(new String(body, UTF_8));
                            channel.basicAck(envelope.getDeliveryTag(), false);
                        }
                    });

            return this;
        } catch (final IOException e) {
            throw new OldStackException(e);
        }
    }

    public void close() {
        try {
            if (channel != null && channel.isOpen()) {
                channel.close();
            }

            if (connection != null && connection.isOpen()) {
                connection.close();
            }
        } catch (final IOException | TimeoutException e) {
            throw new OldStackException(e);
        }
    }

}
