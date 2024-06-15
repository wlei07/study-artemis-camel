package be.yangchun.artemis_study;

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

    private final FirstProcessor firstProcessor;

    @Override
    public void configure() throws Exception {
        from(consumer)
                .log("Received Message.")
                .to("direct:processFile");

        from("direct:processFile")
                .log("Processing Message - Content: ${body}")
                .process(firstProcessor)
                .log("Content after processing: ${body}")
                .stop();
    }
}
