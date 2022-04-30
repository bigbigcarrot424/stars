package com.fangshuo.stars.resp;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentListResp {
    Long id;
    Long blogId;
    Long commentatorId;
    String commentatorName;
    Timestamp commentTime;
    String content;
    String avatar;
}
