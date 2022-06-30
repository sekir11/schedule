package com.schedule.application.controller;

import com.schedule.application.payload.GetUserPayload;
import com.schedule.domain.model.User;
import com.schedule.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;


    @GetMapping(path = "search")
    List<GetUserPayload> searchUser(@RequestParam("name") String name) {
        List<User> users = userService.searchUsers(name);
        return users.stream().map(GetUserPayload::of).collect(Collectors.toList());
    }

}
