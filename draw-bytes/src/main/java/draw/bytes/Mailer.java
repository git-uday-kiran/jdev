package draw.bytes;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class Mailer {

    private static final SesClient SES_CLIENT = getSesClient();
    private Body.Builder bodyBuilder;
    private Message.Builder messageBuilder;
    private Destination.Builder destinationBuilder;
    private SendEmailRequest.Builder emailRequestBuilder;

    public Mailer() {
        emailRequestBuilder = SendEmailRequest.builder();
        destinationBuilder = Destination.builder();
        messageBuilder = Message.builder();
        bodyBuilder = Body.builder();
    }

    private static SesClient getSesClient() {
        return SesClient.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(AwsCredentialsImpl::new)
                .build();
    }

    public static Mailer newMailer() {
        return new Mailer();
    }

    public Mailer from(String sender) {
        emailRequestBuilder.source(sender);
        return this;
    }

    public Mailer to(String receiver) {
        destinationBuilder.toAddresses(receiver);
        return this;
    }

    public Mailer cc(String recipient) {
        destinationBuilder.ccAddresses(recipient);
        return this;
    }

    public Mailer bodyText(String textData) {
        Content textContent = Content.builder()
                .charset(StandardCharsets.UTF_8.name())
                .data(textData)
                .build();
        bodyBuilder.text(textContent);
        return this;
    }

    public Mailer bodyHtml(String htmlData) {
        Content htmlContent = Content.builder()
                .charset(StandardCharsets.UTF_8.name())
                .data(htmlData)
                .build();
        bodyBuilder.html(htmlContent);
        return this;
    }

    public Mailer subject(String subject) {
        Content subjectContent = Content.builder()
                .data(subject)
                .charset(StandardCharsets.UTF_8.name())
                .build();
        messageBuilder.subject(subjectContent);
        return this;
    }

    public void send() {
        Destination destination = destinationBuilder.build();
        Body body = bodyBuilder.build();
        Message message = messageBuilder.body(body).build();
        SendEmailRequest sendEmailRequest = emailRequestBuilder.message(message).destination(destination).build();

        System.out.println("Sending.... " + sendEmailRequest.message());
        try {
            SES_CLIENT.sendEmail(sendEmailRequest);
        } catch (SesException exception) {
            exception.printStackTrace();
        }
    }

    public void sendAsync() {
        CompletableFuture.runAsync(this::send);
    }

}

