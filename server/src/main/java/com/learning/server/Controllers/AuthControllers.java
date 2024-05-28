package com.learning.server.Controllers;

import com.learning.server.Config.JwtProvider;
import com.learning.server.Model.User;
import com.learning.server.Repository.UserRepository;
import com.learning.server.Request.LoginRequest;
import com.learning.server.Response.AuthResponse;
import com.learning.server.Service.UserService;
import com.learning.server.Service.UserServiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControllers {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceDetailService userServiceDetailService;

    @PostMapping("/signup")
    public AuthResponse registerUser(@RequestBody User user) throws Exception {
        User isExists = userService.findByUserEmail(user.getEmail());
        if(isExists!=null){
            throw new Exception("Email already have an account...");
        }
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setGender(user.getGender());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User register=  userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getEmail(),newUser.getPassword());
        System.out.println("authentication ========="+authentication);
        String token = JwtProvider.generateToken(authentication);
        System.out.println("token ========="+token);
        AuthResponse result = new AuthResponse(token,"Registered successfully");

        return result;
    }

    @PostMapping("/login")
    public AuthResponse LoginUser(@RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authentication=
                authenticate(loginRequest.getEmail(),loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token,"Login successfully");
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails= userServiceDetailService.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("invalid email with emailId: "+email);
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Password does not matches");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,
                null,userDetails.getAuthorities());

    }

}
