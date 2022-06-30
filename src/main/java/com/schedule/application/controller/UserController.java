package com.schedule.application.controller;

import com.schedule.application.session.SessionContext;
import com.schedule.application.session.SessionUser;
import com.schedule.domain.model.User;
import com.schedule.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionContext sessionContext;

    @GetMapping(path = "/sign-up-page")
    public String getSignUpPage() {
        return "sign-up";
    }

    @PostMapping(path = "/sign-up")
    public String signUp(String name, String password, String address) {
        User user = userService.signUp(name, password, address);
        sessionContext.setSessionUser(SessionUser.of(user));
        return "redirect:events";
    }
}
