package com.fangshuo.stars.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CircleBlog {
    Long id;
    Long authorId;
    Long circleId;
    Timestamp publishTime;
    String content;
    Integer voteNum;
    Integer opposeNum;
    Integer commentNum;
}
