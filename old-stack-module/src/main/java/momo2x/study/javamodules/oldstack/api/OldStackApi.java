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

    public OldStackApi initialize() {
        getConfig().init();
        return this;
    }

    public OldStackApi addConsumer(OldStackConsumer consumer) {
        getConfig().addConsumer(consumer);
        return this;
    }

    public void close() {
        getConfig().close();
    }

//    public static void main(String[] args) {
//        OldStackApi api = new OldStackApi()
//                .initialize()
//                .addConsumer(new OldStackConsumer() {
//                    @Override
//                    public void consume(String message) {
//                        System.out.println("[Old Stack] Message = " + message);
//                    }
//                });
//
//        getRuntime().addShutdownHook(
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        api.close();
//                    }
//                }));
//    }

}
