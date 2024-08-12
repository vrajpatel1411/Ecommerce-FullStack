package com.myeccom.backend.controller;

import com.myeccom.backend.Exception.UserException;
import com.myeccom.backend.config.JwtProvider;
import com.myeccom.backend.model.User;
import com.myeccom.backend.repository.UserRepository;
import com.myeccom.backend.request.LoginRequest;
import com.myeccom.backend.response.AuthResponse;
import com.myeccom.backend.service.CustomerUserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtprovider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserServiceImplementation customerUserServiceImplementation;

//    public AuthController(UserRepository userRepository,JwtProvider jwtprovider,PasswordEncoder passwordEncoder,CustomerUserServiceImplementation customerUserServiceImplementation){
//        this.jwtprovider=jwtprovider;
//        this.passwordEncoder=passwordEncoder;
//        this.customerUserServiceImplementation=customerUserServiceImplementation;
//        this.userRepository=userRepository;
//    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandle(@RequestBody User user) throws UserException{
        String email=user.getEmail();
        String password=user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();

        User isEmailExist= userRepository.findByEmail(email);

        if(isEmailExist!=null){
            throw new UserException("Email is already present");
        }

        User createdUser=new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User savedUser= userRepository.save(createdUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt=jwtprovider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Successfully user created");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
        String userName=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        Authentication authentication=authenticate(userName,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtprovider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse(jwt,"Successfully signin ");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails= customerUserServiceImplementation.loadUserByUsername(userName);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        System.out.println(userDetails);
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
