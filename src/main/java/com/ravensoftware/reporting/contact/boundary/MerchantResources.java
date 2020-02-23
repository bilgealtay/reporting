package com.ravensoftware.reporting.contact.boundary;

import com.ravensoftware.reporting.contact.control.MerchantControl;
import com.ravensoftware.reporting.contact.entity.Merchant;
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
@RequestMapping(path = "/merchants")
public class MerchantResources {

    private MerchantControl merchantControl;

    public MerchantResources(MerchantControl merchantControl) {
        this.merchantControl = merchantControl;
    }

    @ApiOperation(value = "Save merchant to the System ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping
    public ResponseEntity save(@ApiParam(value = "Merchant save a database table", required = true) @Valid @RequestBody Merchant merchant) {
        Merchant save  = merchantControl.save(merchant);
        return ResponseEntity.ok(save);
    }
}
