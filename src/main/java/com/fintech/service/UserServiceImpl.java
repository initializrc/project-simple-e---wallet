package com.fintech.service;


import com.fintech.entity.User;
import com.fintech.model.UserModel;
import com.fintech.model.UserRequestModel;
import com.fintech.repository.UserRepository;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserModel register(UserRequestModel requestModel) {
        User userByUsername = userRepository.findByUsername(requestModel.getUsername());
        if (userByUsername != null && userByUsername.getId() != null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Username already exists");
        User userByEmail= userRepository.findByEmail(requestModel.getEmail());
        if (userByEmail != null && userByEmail.getId() != null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Email already exists");
        User userByPhoneNumber = userRepository.findByPhoneNumber(requestModel.getPhoneNumber());
        if (userByPhoneNumber != null && userByPhoneNumber.getId() !=null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Phone number already exists");


        User user = new User();
        user.setEmail(requestModel.getEmail());
        user.setPassword(requestModel.getPassword());
        user.setUsername(requestModel.getUsername());
        user.setFullName(requestModel.getFullName());
        user.setPhoneNumber(requestModel.getPhoneNumber());
        user.setType(User.Type.valueOf(requestModel.getType().toUpperCase()));
        user = userRepository.save(user);

        UserModel userModel = new UserModel();
        userModel.setType(requestModel.getType().toUpperCase());
        BeanUtils.copyProperties(user, userModel);
        return userModel;
    }

}
