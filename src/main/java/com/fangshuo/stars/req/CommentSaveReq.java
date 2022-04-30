package com.fangshuo.stars.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentSaveReq {
    Long commentatorId;
    Long blogId;
    String content;
}
