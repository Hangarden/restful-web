package com.example.restfulweb.user;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) { //의존성 주입
        this.service = service;
    }

    @GetMapping("/users")
    public List<UserV1> retireveAllUsers() {
        return service.findAll();
    }

    //문자열 형태로 전달 되게 됩니다.
    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<UserV1>> retrieveUser(@PathVariable int id) {
        UserV1 userV1 = service.findOne(id);

        if (userV1 == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }


        //HATEOAS ControllerLinkBuilder -> WebMvcLinkBuilder Resource -> EntityModel

        EntityModel entityModel = EntityModel.of(userV1);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retireveAllUsers());
        entityModel.add(linkTo.withRel("all-users"));
        return ResponseEntity.ok(entityModel);

    }

    @PostMapping("/users")
    public ResponseEntity<UserV1> createUser(@Valid  @RequestBody UserV1 userV1) { //어노테이션에 의해 유효성 체크 진행
        UserV1 savedUserV1 = service.save(userV1);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUserV1.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        UserV1 userV1 = service.deleteById(id);

    if (userV1 == null) {
        throw new UserNotFoundException(String.format("ID[%s] not found", id));
    }
    }
}
