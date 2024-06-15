package be.yangchun.artemis_study.controller;

import be.yangchun.artemis_study.service.DispatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final DispatcherService dispatcherService;

    @PostMapping(value = "/send")
    public ResponseEntity<String> send(@RequestBody String message) {
        dispatcherService.sendMessage(message);
        return new ResponseEntity<>("Message send: " + message, HttpStatus.OK);
    }
}
