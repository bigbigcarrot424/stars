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
public class Message {
    Long id;
    Long senderId;
    Long receiverId;
    String content;
    Timestamp time;
}