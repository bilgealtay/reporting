package com.ravensoftware.reporting.controller;

import com.ravensoftware.reporting.model.request.LoginRequest;
import com.ravensoftware.reporting.model.response.LoginResponse;
import com.ravensoftware.reporting.user.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by bilga
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/merchant/user/login")
public class LoginResources {

    private final LoginService loginService;

    @PostMapping
    public LoginResponse login(@NotNull @Valid @RequestBody LoginRequest request) {
        return loginService.login(request);
    }
}
