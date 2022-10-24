package com.ravensoftware.reporting.customer.resources;

import com.ravensoftware.reporting.customer.control.AcquirerControl;
import com.ravensoftware.reporting.model.entity.Acquirer;
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
@RequestMapping(path = "/acquirers")
public class AcquirerResources {

    private final AcquirerControl acquirerControl;

    @PostMapping
    public ResponseEntity save(@ApiParam(value = "Acquirer save a database table", required = true) @Valid @RequestBody Acquirer acquirer) {
        Acquirer save  = acquirerControl.save(acquirer);
        return ResponseEntity.ok(save);
    }
}
