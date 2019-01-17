package com.rkc.zds.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rkc.zds.dto.UserDto;
import com.rkc.zds.error.UserAlreadyExistException;

public interface UserService {

    @Transactional
	UserDto findByUserName(String userName);

	UserDto findById(Integer id);

	List<UserDto> getUsers();
	
    @Transactional    
    public void updateUser(UserDto user);

    @Transactional    
    public void saveUser(UserDto user);

	UserDto registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

}