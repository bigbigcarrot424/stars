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
public class Blog {
    Long id;
    Long authorId;
    Timestamp publishTime;
    String content;
    Integer voteNum;
    Integer opposeNum;
    Integer commentNum;
}
