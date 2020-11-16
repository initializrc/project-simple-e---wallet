package com.fintech.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fintech.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel extends PersistenceModel {

    private String email;
    private String username;
    private String fullName;
    private String phoneNumber;
    private String type;

}