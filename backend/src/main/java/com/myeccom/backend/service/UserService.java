package com.myeccom.backend.service;


import com.myeccom.backend.Exception.UserException;
import com.myeccom.backend.model.User;

public interface UserService {
    public User findUserByd(Long userId) throws UserException;


    public User findUserProfileByJwt(String jwt) throws UserException;
}
