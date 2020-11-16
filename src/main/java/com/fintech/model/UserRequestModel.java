package com.fintech.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fintech.entity.User;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestModel {

    @NotBlank
    @Email(message = "email invalid")
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    private String username;

    @NotBlank
    @Pattern(regexp = "(^[0-9]+$|^$)", message = "number only")
    private String phoneNumber;

    @NotBlank
    private String type;

}


