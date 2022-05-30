package com.fangshuo.stars.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoUpdateReq {
    Long id;
    String name;
    String selfIntro;
    Integer age;
    String gender;
    String circles;
    String managedCircles;
    String tags;
    Boolean status;
}
