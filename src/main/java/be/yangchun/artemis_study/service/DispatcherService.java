package be.yangchun.artemis_study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatcherService {
    private final JmsTemplate jmsTemplate;
    @Value("${jms.queue}")
    private String jmsQueue;

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(jmsQueue, message);
    }
}
