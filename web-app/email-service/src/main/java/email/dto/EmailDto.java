package email.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

@Validated
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EmailDto(@Email @NotNull @NotEmpty String fromEmail, @NotNull @NotEmpty String fromName, @NotEmpty Collection<String> to, Collection<String> cc, Collection<String> bcc, @NotNull @NotEmpty String subject,
                       @NotNull @NotEmpty String content, boolean isHtml, FileBArray[] files) {

    public EmailDto of(String fromEmail, Collection<String> to, String subject, String content) {
        return new EmailDto(fromEmail, fromEmail, to, List.of(), List.of(), subject, content, false, new FileBArray[]{});
    }

    public EmailDto of(String fromEmail, String fromName, Collection<String> to, String subject, String content) {
        return new EmailDto(fromEmail, fromName, to, List.of(), List.of(), subject, content, false, new FileBArray[]{});
    }

    public record FileBArray(byte[] data, String fileName) {
    }
}
