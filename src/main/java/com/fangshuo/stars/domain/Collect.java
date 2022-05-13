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
public class Collect {
    Long id;
    Long collectorId;
    Long blogId;
    Timestamp collectTime;
}
