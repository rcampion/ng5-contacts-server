package com.rkc.zds.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.rkc.zds.dto.ContactDto;
import com.rkc.zds.dto.UserDto;
import com.rkc.zds.error.UserAlreadyExistException;

public interface UserService {
    Page<UserDto> findUsers(Pageable pageable);
    
    @Transactional
	UserDto findByUserName(String userName);

	UserDto findById(Integer id);

	List<UserDto> getUsers();
	
    UserDto getUser(int id);  
	
    @Transactional    
    public void updateUser(UserDto user);
    
    @Transactional  
	void deleteUser(int id);
    
    @Transactional    
    public void saveUser(UserDto user);

	UserDto registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

	Page<UserDto> searchUsers(Pageable pageable, Specification<UserDto> spec);

}