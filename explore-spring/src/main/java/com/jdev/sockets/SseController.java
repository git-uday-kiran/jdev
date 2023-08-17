package com.jdev.sockets;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("sockets")
@CrossOrigin("http://127.0.0.1:5500")
public class SseController {

    private static final List<SseEmitter> EMITTERS = new ArrayList<>();

    @GetMapping("register")
    public SseEmitter registerSse() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        log.info("registered emitter '{}'", emitter);
        EMITTERS.add(emitter);
        return emitter;
    }

    @PostMapping("post-question")
    public void postQuestion(@RequestBody final String question) {
        EMITTERS.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().name("post-question").data(question));
                log.info("sent data '{}' to '{}'", question, emitter);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
