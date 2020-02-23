package com.ravensoftware.reporting.contact.boundary;

import com.ravensoftware.reporting.contact.entity.CustomerInfo;
import com.ravensoftware.reporting.contact.control.CustomerInfoControl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping(path = "/customer-infos")
public class CustomerInfoResources {

    private CustomerInfoControl customerInfoControl;

    public CustomerInfoResources(CustomerInfoControl customerInfoControl) {
        this.customerInfoControl = customerInfoControl;
    }

    @ApiOperation(value = "Save customer info to the System ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping
    public ResponseEntity save(@ApiParam(value = "Customer Info save a database table", required = true) @Valid @RequestBody CustomerInfo customerInfo) {
        CustomerInfo save  = customerInfoControl.save(customerInfo);
        return ResponseEntity.ok(save);
    }
}
