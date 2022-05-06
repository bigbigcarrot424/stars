package com.fangshuo.stars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfo {
    Long id;
    String selfIntro;
    Integer age;
    String gender;
    String circles;
    String managedCircles;
    String tags;
    Boolean status;
}
