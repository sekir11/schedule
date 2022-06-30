package com.schedule.application.controller;

import com.schedule.application.session.SessionContext;
import com.schedule.application.session.SessionUser;
import com.schedule.common.exception.ApplicationException;
import com.schedule.domain.model.User;
import com.schedule.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    private final SessionContext sessionContext;

    @GetMapping("/login-page")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("login")
    public String login(String name, String password, Model model) {
        try {
            User user = userService.login(name, password);
            sessionContext.setSessionUser(SessionUser.of(user));
            return "redirect:events";

        } catch (ApplicationException exception) {
            model.addAttribute("error", "ログインに失敗しました。");
            return "login";
        }
    }
}
