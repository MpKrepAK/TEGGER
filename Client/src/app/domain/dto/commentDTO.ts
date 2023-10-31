import {ECommentStars} from "../enums/eCommentStars";

export interface CommentDTO{
  senderName : string,
  commentStar : ECommentStars,
  message : string
}
