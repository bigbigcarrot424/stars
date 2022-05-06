package com.fangshuo.stars.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoResp {
    Long id;
    String selfIntro;
    Integer age;
    String gender;
    String circles;
    String managedCircles;
    String tags;
    Boolean status;
}
