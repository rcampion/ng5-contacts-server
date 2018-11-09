package com.rkc.zds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rkc.zds.dto.ContactDto;
import com.rkc.zds.dto.UserDto;
import com.rkc.zds.repository.UserRepository;
import com.rkc.zds.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
	public UserDto findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

	@Override
	public UserDto findById(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<UserDto> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public void updateUser(UserDto user) {
		userRepository.saveAndFlush(user);
		
	}
	
	@Override
	public void saveUser(UserDto user) {
		userRepository.save(user);
	}
}
