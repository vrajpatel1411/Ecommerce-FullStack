package com.myeccom.backend.service;

import com.myeccom.backend.Exception.UserException;
import com.myeccom.backend.config.JwtProvider;
import com.myeccom.backend.model.User;
import com.myeccom.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{


    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    UserServiceImplementation(UserRepository userRepository,JwtProvider jwtProvider){
        this.jwtProvider=jwtProvider;
        this.userRepository=userRepository;
    }
    @Override
    public User findUserByd(Long userId) throws UserException {
        Optional<User> userOptional=userRepository.findById(userId);

        if(userOptional.isPresent()){
            return userOptional.get();
        }

        throw new UserException("User not  found with id "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email=jwtProvider.getEmailFromToken(jwt);
        User user=userRepository.findByEmail(email);
        if(user==null)
        {
            throw new UserException("User not  found with email: "+email);
        }
        return user;
    }
}
