package momo2x.study.javamodules.consumer.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import momo2x.study.javamodules.oldstack.api.OldStackApi;
import momo2x.study.javamodules.oldstack.api.OldStackConsumer;
import org.springframework.stereotype.Component;

@Component
public class OldStackApiInitializer {

    private OldStackApi oldStackApi;

    @PostConstruct
    public void postConstruct() {
        oldStackApi = new OldStackApi();
        oldStackApi.initialize();
        OldStackConsumer consumer = message -> System.out.println("message = " + message);
        oldStackApi.addConsumer(consumer);
    }

    @PreDestroy
    public void preDestroy() {
        oldStackApi.close();
    }
}
