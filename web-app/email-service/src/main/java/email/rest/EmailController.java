package email.rest;

import email.dto.EmailDto;
import email.service.EmailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class EmailController{

    private final EmailService emailService;

    @PostMapping(value = "sendEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid @NotNull EmailDto emailDto) {
        log.info("Sending email... {}", emailDto);
        emailService.sendEmail(emailDto);
        return ResponseEntity.ok().build();
    }

//    @ExceptionHandler(Throwable.class)
//    public ResponseEntity<String> handle(Throwable throwable) {
//        return ResponseEntity.internalServerError().body(throwable.toString());
//    }
}
