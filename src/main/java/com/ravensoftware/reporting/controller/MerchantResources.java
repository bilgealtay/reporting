package com.ravensoftware.reporting.controller;

import com.ravensoftware.reporting.customer.merchant.MerchantControl;
import com.ravensoftware.reporting.model.entity.Merchant;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by bilga
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/merchant")
public class MerchantResources {

    private final MerchantControl merchantControl;

    @PostMapping
    public ResponseEntity save(@ApiParam(value = "Merchant save a database table", required = true) @Valid @RequestBody Merchant merchant) {
        Merchant save  = merchantControl.save(merchant);
        return ResponseEntity.ok(save);
    }

}
