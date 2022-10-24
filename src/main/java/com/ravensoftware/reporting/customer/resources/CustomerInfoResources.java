package com.ravensoftware.reporting.customer.resources;

import com.ravensoftware.reporting.customer.control.CustomerInfoControl;
import com.ravensoftware.reporting.model.entity.CustomerInfo;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by bilga on 21-02-2020
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customer-infos")
public class CustomerInfoResources {

    private final CustomerInfoControl customerInfoControl;

    @PostMapping
    public ResponseEntity save(@ApiParam(value = "Customer Info save a database table", required = true) @Valid @RequestBody CustomerInfo customerInfo) {
        CustomerInfo save  = customerInfoControl.save(customerInfo);
        return ResponseEntity.ok(save);
    }
}
