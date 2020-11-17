package com.fintech.service;

import com.fintech.entity.User;
import com.fintech.model.UserModel;
import com.fintech.repository.UserRepository;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserModel saveOrUpdate(UserModel entity) { return null; }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserModel register(UserModel entity) {
        User userByUsername = userRepository.findByUsername(entity.getUsername());
        if (userByUsername != null && userByUsername.getId() != null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Username already exists");
        User userByEmail= userRepository.findByEmail(entity.getEmail());
        if (userByEmail != null && userByEmail.getId() != null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Email already exists");
        User userByPhoneNumber = userRepository.findByPhoneNumber(entity.getPhoneNumber());
        if (userByPhoneNumber != null && userByPhoneNumber.getId() !=null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Phonenumber already exists");

        entity.setType(entity.getType().toUpperCase());

        User user = new User();
        user.setEmail(entity.getEmail());
        user.setUsername(entity.getUsername());
        user.setFullName(entity.getFullName());
        user.setPhoneNumber(entity.getPhoneNumber());
        user.setType(User.Type.valueOf(entity.getType()));
        user = userRepository.save(user);


        BeanUtils.copyProperties(user, entity);
        return entity;
    }

    @Override
    public UserModel delete(UserModel entity) {
        return null;
    }

    @Override
    public UserModel deleteById(Integer integer) {
        return null;
    }

    @Override
    public UserModel findById(Integer integer) {
        return null;
    }

    @Override
    public List<UserModel> findAll() {
        return null;
    }

    @Override
    public Long countAll() {
        return null;
    }
}
