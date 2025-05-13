package momo2x.study.javamodules.producer.independentproducer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static momo2x.study.javamodules.producer.independentproducer.Config.TOPIC_EXCHANGE_NAME;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producer")
public class Controller {

    private final String routingKey;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<String> sendToQueue(@RequestBody final String body) {
        final var sendRoutingKey = routingKey.replace("#", "producer.sendToQueue");
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, sendRoutingKey, body);

        return ResponseEntity
                .status(CREATED)
                .body("Sent \"%s\" to \"%s\"".formatted(body, sendRoutingKey));
    }

}
