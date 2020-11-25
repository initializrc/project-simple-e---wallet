package com.fintech.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fintech.util.FieldsValueMatch;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldsValueMatch.List({
    @FieldsValueMatch(
            field = "password",
            fieldMatch = "verifyPassword",
            message = "password fields must match!"
    )
})
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

    @NotBlank
    private String password;

    @NotBlank
    private String verifyPassword;
}


