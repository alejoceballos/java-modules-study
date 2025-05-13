package momo2x.study.javamodules.oldstack.api;

import momo2x.study.javamodules.oldstack.Config;

public class OldStackApi {

    private static Config config;

    private static Config getConfig() {
        if (config == null) {
            config = new Config();
        }

        return config;
    }

    public void initialize() {
        getConfig().init();
    }

     public void addConsumer(OldStackConsumer consumer) {
        getConfig().addConsumer(consumer);
     }

     public void close() {
        getConfig().close();
     }

}
