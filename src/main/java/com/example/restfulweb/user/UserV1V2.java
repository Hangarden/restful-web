package com.example.restfulweb.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //여기에 필드에 쓴 모든생성자만 만들어줌
//@JsonIgnoreProperties(value = {"password","ssn"})
@JsonFilter("UserInfoV2") //UserInfo 필터 걸려있는 상태
@NoArgsConstructor
public class UserV1V2 extends UserV1 {
    private String grade;
}


//@NoArgsConstructor
//@RequiredArgsConstructor
//@AllArgsConstructor
//public class User {
//
//    private Long id;
//
//    @NonNull
//    private String name;
//
//    @NonNull
//    private String pw;
//
//    private int age;
//
//}

//    User user1 = new User(); // @NoArgsConstructor
//    User user2 = new User("user2", "1234"); // @RequiredArgsConstructor
//    User user3 = new User(1L, "user3", "1234", null); // @AllArgsConstructor