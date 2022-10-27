package com.example.restfulweb.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service) { //의존성 주입
        this.service = service;
    }

    @GetMapping("/v1/users")
    public MappingJacksonValue retireveAllUsersV1() {

        List<UserV1> userV1s = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDatae", "password", "ssn"); //필터에 제외 될 것

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter); //User에 필터를 적용하겠다.

        MappingJacksonValue mapping = new MappingJacksonValue(userV1s); //user값을 MappingJacksonValue로 변환
        mapping.setFilters(filters); //데이터에 Filters적용

        return mapping;
    }

    //문자열 형태로 전달 되게 됩니다.
    @GetMapping("/v1/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=1")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
//    @GetMapping(value = "/v1/users/{id}", produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        UserV1 userV1 = service.findOne(id);

        if (userV1 == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDatae", "password", "ssn"); //필터에 제외 될 것

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter); //User에 필터를 적용하겠다.

        MappingJacksonValue mapping = new MappingJacksonValue(userV1); //user값을 MappingJacksonValue로 변환
        mapping.setFilters(filters); //데이터에 Filters적용


        return mapping; //MappingJacksonValue에 mapping 리태

    }

//    @GetMapping(value = "/users/{id}", params = "version=2")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
@GetMapping("/v2/users/{id}")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        UserV1 userV1 = service.findOne(id);

        if (userV1 == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // User -> User2
        UserV1V2 userV2 = new UserV1V2();
        BeanUtils.copyProperties(userV1, userV2);
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade"); //필터에 제외 될 것

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter); //User에 필터를 적용하겠다.

        MappingJacksonValue mapping = new MappingJacksonValue(userV2); //user값을 MappingJacksonValue로 변환
        mapping.setFilters(filters); //데이터에 Filters적용


        return mapping; //MappingJacksonValue에 mapping 리태

    }

}

