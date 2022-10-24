package com.ravensoftware.reporting.user.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravensoftware.reporting.config.ApiClientConfig;
import com.ravensoftware.reporting.config.RestTemplateConfig;
import com.ravensoftware.reporting.model.request.LoginRequest;
import com.ravensoftware.reporting.model.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by bilga on 22-10-2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginControl {

    private final ApiClientConfig apiClientConfig;
    private final RestTemplateConfig restTemplateConfig;

    public Optional<LoginResponse> login(LoginRequest request) {
        log.info("ReportingApiClient login start");
        try {
            ObjectMapper objectMapper = restTemplateConfig.objectMapper();
            String requestBody = objectMapper.writeValueAsString(request);

            HttpHeaders headers = getHttpHeaders();
            HttpEntity<String> postRequest = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Object> objectResponse = postRequest(getPath(apiClientConfig.getLogin()), postRequest);
            log.info("ReportingApiClient login ok");
            return Optional.of(objectMapper.convertValue(objectResponse.getBody(), LoginResponse.class));
        } catch (Exception e) {
            log.error("login ", e);
        }
        return Optional.empty();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.valueOf("application/json;charset=UTF-8"));
        return headers;
    }

    private String getPath(String path) {
        return apiClientConfig.getPath().concat(path);
    }

    private ResponseEntity<Object> postRequest(String path, HttpEntity<String> request) {
        log.debug("request started. url:[{}]", path);
        return restTemplateConfig.restTemplate().exchange(path, HttpMethod.POST, request, Object.class);
    }
}
