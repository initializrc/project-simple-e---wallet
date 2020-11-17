package com.fintech.controller;

import com.fintech.model.UserModel;
import com.fintech.model.UserRequestModel;
import com.fintech.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/rest/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserModel save(@RequestBody @Valid UserRequestModel request, BindingResult result, HttpServletResponse response) throws IOException {
        UserModel userModel = new UserModel();
        request.setType(request.getType().toUpperCase());
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return userModel;
        } else {
            BeanUtils.copyProperties(request, userModel);
            return userService.register(userModel);
        }
    }
}
