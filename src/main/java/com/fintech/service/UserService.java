package com.fintech.service;

import com.fintech.model.UserModel;
import com.fintech.model.UserRequestModel;

public interface UserService {

    UserModel register(UserRequestModel requestModel);

}
