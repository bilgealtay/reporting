package com.ravensoftware.reporting.user.boundary;

import com.ravensoftware.reporting.user.control.UserControl;
import com.ravensoftware.reporting.user.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Created by bilga on 20-02-2020
 */
@RestController
@RequestMapping("/users")
public class UserResources {

    private UserControl userControl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResources(UserControl userControl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userControl = userControl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ApiOperation(value = "Save User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping(value = "/save")
    public void signUp(@ApiParam(value = "User entity", required = true) @NotNull @RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userControl.save(user);
    }
}

