package com.rkc.zds.controller;

import com.rkc.zds.dto.LoginDTO;
import com.rkc.zds.dto.UserDto;
import com.rkc.zds.service.AuthenticationService;
import com.rkc.zds.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;
/*
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public UserDto authenticate(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws Exception{
    	//String clientOrigin = request.getHeader("origin");
//createDefaultAccount();
    	response.setHeader("Access-Control-Allow-Origin","http://localhost:4200");
    	response.setHeader("Access-Control-Allow-Credentials", "true");
    	return authenticationService.authenticate(loginDTO,response);
    }
*/    
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public UserDto authenticate(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) throws Exception{
//    	response.setHeader("Access-Control-Allow-Origin","https://www.zdslogic.com");
//    	response.setHeader("Access-Control-Allow-Credentials", "true");

    	return authenticationService.authenticate(loginDTO, request, response);
    }
    
    private void createDefaultAccount() {
        UserDto user = new UserDto();
//        user.setId(1);
        user.setLogin("Guest");
        user.setUserName("Guest");
        user.setFirstName("Guest");
        user.setLastName("Testing");
//        user.setProfile(Profile.ADMIN);
        user.setPassword("Testing");
//        user.setAuthorities(authorities.get(user.getProfile()));
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(1);
        userService.saveUser(user);		
	}

	@RequestMapping(value = "/logout",method = RequestMethod.GET)
    public void logout(){
        authenticationService.logout();
    }
}
