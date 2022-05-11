package com.fangshuo.stars.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageSaveReq {
    Long senderId;
    Long receiverId;
    String content;
}
