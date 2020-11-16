package com.fintech.repository;

import com.fintech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);


}
