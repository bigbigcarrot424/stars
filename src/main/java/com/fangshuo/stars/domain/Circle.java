package com.fangshuo.stars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Circle {
    Long id;
    String circleName;
    String intro;
    Long managerId;
    Timestamp createdTime;
    String tags;
}


