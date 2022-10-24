package com.ravensoftware.reporting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravensoftware.reporting.model.request.LoginRequest;
import com.ravensoftware.reporting.user.control.UserDetailsServiceImpl;
import com.ravensoftware.reporting.user.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by bilga
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LoginResources.class)
public class LoginResourcesTest {

    public static final String PASSWORD = "12345";
    public static final String TEST_EMAIL = "bilge.altay@test.com";
    public static final String NOT_VALID_EMAIL = "bilge.altay";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LoginService loginService;
    @MockBean
    UserDetailsServiceImpl userDetailsService;

    ObjectMapper mapper =  new ObjectMapper();

    @Test
    public void ShouldReturnBadRequestWhenLoginRequestDoesNotExist() throws Exception {

        LoginRequest loginRequest = new LoginRequest();

        mockMvc.perform(post("/merchant/user/login")
                        .content(mapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void ShouldReturnBadRequestWhenEmailIsNotValid() throws Exception {

        LoginRequest loginRequest = new LoginRequest(NOT_VALID_EMAIL, PASSWORD);

        mockMvc.perform(post("/merchant/user/login")
                        .content(mapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldLogin() throws Exception {

        LoginRequest loginRequest = new LoginRequest(TEST_EMAIL, PASSWORD);

        mockMvc.perform(post("/merchant/user/login")
                        .content(mapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

}
