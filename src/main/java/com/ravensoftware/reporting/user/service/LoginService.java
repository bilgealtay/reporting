package com.ravensoftware.reporting.user.service;

import com.ravensoftware.reporting.exception.ReportingApiException;
import com.ravensoftware.reporting.model.request.LoginRequest;
import com.ravensoftware.reporting.model.response.LoginResponse;
import com.ravensoftware.reporting.user.control.LoginControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by bilga on 22-10-2022
 */
@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginControl loginControl;

    public LoginResponse login(LoginRequest request) {
        return loginControl.login(request)
                .orElseThrow(new ReportingApiException("Check login info", HttpStatus.BAD_REQUEST));
    }
}
