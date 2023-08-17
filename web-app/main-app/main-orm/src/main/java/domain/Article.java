package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Article extends BaseAuditingEntity{

    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private ArticleStatus articleStatus;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<ReceivedFile> attachedFiles;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "article")
    private Set<Comment> comments = new HashSet<>();

}
