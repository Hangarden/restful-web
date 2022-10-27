package com.example.restfulweb.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor //여기에 필드에 쓴 모든생성자만 만들어줌
//@JsonIgnoreProperties(value = {"password","ssn"})
@NoArgsConstructor //Default생성자 만들어줌
//@JsonFilter("UserInfo") //UserInfo 필터 걸려있는 상황
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class UserV1 {

    private Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해주세요")
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요")
    private String name;
    @Past
    @ApiModelProperty(notes = "사용자 등록일을 입력해주세요")
    private Date joinDate;
    @ApiModelProperty(notes = "사용자 비밀번호를 입력해주세요")
    private String password;
    @ApiModelProperty(notes = "사용자 주민번호를 입력해주세요")
    private String ssn;
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