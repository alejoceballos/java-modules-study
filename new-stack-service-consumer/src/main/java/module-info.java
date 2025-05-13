open module NewStackServiceConsumer {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires jakarta.annotation;

    requires OldStackModule;
}