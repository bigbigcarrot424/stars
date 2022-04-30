package com.fangshuo.stars.resp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogListResp {
    Long id;
    Long authorId;
    String authorName;
    Timestamp publishTime;
    String content;
    Integer voteNum;
    Integer opposeNum;
    Integer commentNum;
    String avatar;
}
