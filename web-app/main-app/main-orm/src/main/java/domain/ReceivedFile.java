package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.File;
import java.sql.SQLType;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ReceivedFile {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    UUID id;

    @Enumerated(EnumType.STRING)
    private FileGroup fileGroup;

    private LocalDateTime createdDateTime;

    private String originalFileName;

    private String storedName;

    public ReceivedFile(final FileGroup fileGroup, final LocalDateTime createdDateTime, final String originalFileName, final  String storedName) {
        this.fileGroup = fileGroup;
        this.createdDateTime = createdDateTime;
        this.originalFileName = originalFileName;
        this.storedName = storedName;
    }

    enum FileGroup {
        NOTE_ATTACHMENT("attachments");
        String path;
        FileGroup(final String path) {
            this.path = path;
        }
    }
}
