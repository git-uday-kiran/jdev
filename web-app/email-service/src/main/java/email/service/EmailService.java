package email.service;

import email.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static email.utils.EmailUtil.toInternetAddressArray;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(EmailDto email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            var message = new MimeMessageHelper(mimeMessage, String.valueOf(StandardCharsets.UTF_8));

            message.setTo(toInternetAddressArray(email.to()));
            message.setCc(toInternetAddressArray(email.cc()));
            message.setBcc(toInternetAddressArray(email.bcc()));
            message.setFrom(email.fromEmail(), email.fromName());

            message.setSubject(email.subject());
            message.setText(email.content(), email.isHtml());

            EmailDto.FileBArray[] files = email.files() == null ? new EmailDto.FileBArray[0] : email.files();
            for (var file : files) {
                message.addAttachment(file.fileName(), new ByteArrayResource(file.data()));
            }

            mailSender.send(mimeMessage);
            log.info("Email Sent subject : {}", email.subject());
        } catch (IOException | MessagingException exception) {
            throw new RuntimeException("Failed to send email", exception);
        }
    }
}
