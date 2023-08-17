package dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ArticleSummeryDto {
    private Long id;
    private String title;
    private String content;
    private String username;
}
