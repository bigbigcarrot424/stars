package com.fangshuo.stars.resp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LikesListByUserResp {
    Long id;
    Long blogId;
    Long commentatorId;
    Boolean isLike;
    Timestamp commentTime;
}
