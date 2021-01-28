package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.User;
/*
import org.springframework.security.core.userdetails.UserDetailsService;
*/

public interface UserService /*extends UserDetailsService */{

    User findByEmail(String email);

    Iterable<User> getUsers();

    User createUser(User user) throws Exception;

    User getUserById(Long id) throws Exception;

    User updateUser(User user) throws Exception;

    void deleteUser(Long id) throws Exception;

}
