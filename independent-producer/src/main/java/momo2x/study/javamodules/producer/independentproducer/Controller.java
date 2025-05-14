package momo2x.study.javamodules.producer.independentproducer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static momo2x.study.javamodules.producer.independentproducer.Config.ROUTING_KEY_1;
import static momo2x.study.javamodules.producer.independentproducer.Config.ROUTING_KEY_2;
import static momo2x.study.javamodules.producer.independentproducer.Config.TOPIC_EXCHANGE_NAME;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producer")
public class Controller {

    private static final String ROUTING_KEY_SUFFIX = "producer.sendToQueue";

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<String> sendToQueue(@RequestBody final String body) {
        final var message = body + " - " + UUID.randomUUID();
        rabbitTemplate.convertAndSend(
                TOPIC_EXCHANGE_NAME,
                ROUTING_KEY_1.replace("#", ROUTING_KEY_SUFFIX),
                message + " (queue-1)");

        rabbitTemplate.convertAndSend(
                TOPIC_EXCHANGE_NAME,
                ROUTING_KEY_2.replace("#", ROUTING_KEY_SUFFIX),
                message + " (queue-2)");

        return ResponseEntity
                .status(CREATED)
                .body("Sent \"%s\" message".formatted(message));
    }

}
