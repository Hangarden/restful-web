package com.example.restfulweb.helloworld;
//lombok

import lombok.AllArgsConstructor;
import lombok.Data; //setter 와 getter 를 사용하지 않아도 된다.
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //생성자 만들어주는 어노테이션
@NoArgsConstructor
public class HellowWorldBean {
    private String message;
}
