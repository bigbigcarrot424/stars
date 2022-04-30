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
public class Comment {
    Long id;
    Long blogId;
    Long commentatorId;
    String content;
    Timestamp commentTime;
}
