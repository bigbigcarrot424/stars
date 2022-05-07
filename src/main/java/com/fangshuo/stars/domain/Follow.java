package com.fangshuo.stars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    Long id;
    Long likeId;
    Long belikedId;
    Timestamp followTime;
}
