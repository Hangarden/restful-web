package com.example.restfulweb.helloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    //GET
    // /hello-world (endpoint)
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HellowWorldBean hellowWorldBean() {
        return new HellowWorldBean("Hello World"); //json 형태로 반환해준다. responsebody에 담지 않더라도
    }
}
