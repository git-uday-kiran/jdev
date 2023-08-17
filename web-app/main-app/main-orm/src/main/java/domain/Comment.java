package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends BaseEntity {

    private String content;

    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus = CommentStatus.AWAITING_APPROVAL;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @Column(nullable = false)
    private Long parentCommentId;

    public Comment(final String content, final Article article) {
        this.content = content;
        this.article = article;
    }

    public Comment(final String content, final Article article, final Long parentCommentId) {
        this.content = content;
        this.article = article;
        this.parentCommentId = parentCommentId;
    }

    public void addChildComment(final Comment childComment) {
        childComment.parentCommentId = getId();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o != getClass()) return false;
        final Comment comment = (Comment) o;
        return Objects.equals(getId(), comment.getId()) && Objects.equals(getArticle(), comment.getArticle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article);
    }
}
