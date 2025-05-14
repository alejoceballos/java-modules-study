package momo2x.study.javamodules.consumer;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import momo2x.study.javamodules.oldstack.api.OldStackApi;
import org.springframework.stereotype.Component;

@Component
public class OldStackApiAdapter {

    private OldStackApi api;

    @PostConstruct
    public void init() {
        api = new OldStackApi()
                .initialize()
                .addConsumer(this::printMessage);
    }

    @PreDestroy
    public void destroy() {
        api.close();
    }

    private void printMessage(final String message) {
        System.out.println("[New Stack] Message = " + message);
    }

}
