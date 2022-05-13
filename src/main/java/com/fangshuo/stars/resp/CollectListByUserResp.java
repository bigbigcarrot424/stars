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
public class CollectListByUserResp {
    Long id;
    Long collectorId;
    Long blogId;
    Timestamp collectTime;
}
