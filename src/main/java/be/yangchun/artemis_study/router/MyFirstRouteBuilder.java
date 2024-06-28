package be.yangchun.artemis_study.router;

import be.yangchun.artemis_study.processor.FirstProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MyFirstRouteBuilder extends RouteBuilder {
    @Value("${app.queue.consume}")
    private String consumer;
    @Value("${app.queue.produce}")
    private String producer;

    private final FirstProcessor firstProcessor;

    @Override
    public void configure() {
        from(consumer)
                .log("Received Message.")
                .to("direct:processFile");

        from("direct:processFile")
                .log("Processing Message - Content: ${body}")
                .process(firstProcessor)
                .log("Content after processing: ${body}")
                .to(producer)
                .stop();

        from("timer://sampleTimer?repeatCount=1")
                .log("Starting Router")
                .setBody(constant("Simple Message"))
                .to("{{app.queue.produce}}");
        from("{{app.queue.produce}}")
                .log("Data Consumed from Queue: ${body}")
                .end();
    }
}
