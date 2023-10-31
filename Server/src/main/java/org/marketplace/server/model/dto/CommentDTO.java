package org.marketplace.server.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marketplace.server.model.enums.ECommentStars;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private String senderName;
    private ECommentStars commentStar;
    private String message;

    public CommentDTO(String senderName, ECommentStars commentStar, String message) {
        this.senderName = senderName;
        this.commentStar = commentStar;
        this.message = message;
    }
}
