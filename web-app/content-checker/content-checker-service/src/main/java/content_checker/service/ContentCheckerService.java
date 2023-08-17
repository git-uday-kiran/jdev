package content_checker.service;

import contnet_checker.model.ContentCheckOutcome;
import contnet_checker.model.Request;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
public class ContentCheckerService {

    public static void main(String[] args) throws UnknownHostException {
        ApplicationContext context = SpringApplication.run(ContentCheckerService.class, args);
        Environment env = context.getEnvironment();

        log.info("""
                Access URLs:
                -----------------------------------------------------
                Local: http://localhost:{}
                External: http://{}:{}
                Environment: {}
                """, env.getProperty("server.port"), InetAddress.getLocalHost().getHostName(), env.getProperty("server.port"), Arrays.toString(env.getActiveProfiles()));


        JmsTemplate template = context.getBean(JmsTemplate.class);
        Request request = new Request();
        request.setRequestType(Request.RequestType.ARTICLE);
        request.setText("Hey this is my text sample");

        template.convertAndSend("test-queue", request);
    }

}

@Slf4j
@Component
@AllArgsConstructor
class ContentCheckerHandler {
    final JmsTemplate template;
    final ContentChecker contentChecker;

    @JmsListener(destination = "${jms.content-checker-request-queue}")
    void onMessage(Request msg) {
        log.info("Received message for content check '{}'", msg);
        var result = contentChecker.isOk(msg.getText());
        template.convertAndSend(msg.getPostBackTopic(), result);
    }

    @JmsListener(destination = "outcome")
    void onOutcome(ContentCheckOutcome outcome) {
        log.info("received content check outcome : {}", outcome);
    }
}


@Component
class ContentChecker {

    final List<String> badWords = List.of("fuck", "suck", "ass");
    final List<String> controversial = List.of("party", "politics", "libtard", "freedom", "conspiracy", "snowflake");

    public ContentCheckOutcome isOk(final String text) {
        if (Stream.of(text.split(" ")).anyMatch(badWords::contains)) {
            return ContentCheckOutcome.FAILED;
        }
        if (Stream.of(text.split(" ")).anyMatch(controversial::contains)) {
            return ContentCheckOutcome.MANUAL_REVIEW_NEEDED;
        }
        return ContentCheckOutcome.PASSED;
    }
}
