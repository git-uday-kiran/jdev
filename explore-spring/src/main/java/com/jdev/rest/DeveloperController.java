package com.jdev.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@RestController
@RequestMapping("dev")
public class DeveloperController {

    private static AtomicInteger id = new AtomicInteger(0);
    private static List<Developer> developers = new ArrayList<>();

    @GetMapping
    public Developer dev(@RequestParam(name = "name", defaultValue = "some-dev-name") String name, @RequestParam(name = "title", defaultValue = "some-dev-title") String title) {
        Developer developer = new Developer(id.incrementAndGet(), name, title);
        developers.add(developer);
        return developer;
    }

    @PostMapping(path = "save", consumes = "application/json")
    public Developer save(@RequestBody Developer developer) {
        developers.add(developer);
        log.info("saved, {}", developer);
        return developer;
    }

    @GetMapping("all")
    public List<Developer> getAll() {
        return developers;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> notValid(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body("not valid " + exception);
    }

    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> notSupported(org.springframework.web.HttpRequestMethodNotSupportedException exception) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("my-error", exception.toString());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(json.asText());
    }

}
