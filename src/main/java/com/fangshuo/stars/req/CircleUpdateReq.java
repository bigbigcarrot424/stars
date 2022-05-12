package com.fangshuo.stars.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CircleUpdateReq {
    Long id;
    String circleName;
    String intro;
    Long managerId;
    String tags;
}
