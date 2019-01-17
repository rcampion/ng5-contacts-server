package com.rkc.zds.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rkc.zds.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rkc.zds.dto.AuthorityDto;
import com.rkc.zds.dto.ContactDto;
import com.rkc.zds.dto.UserDto;
import com.rkc.zds.repository.UserRepository;
import com.rkc.zds.repository.AuthorityRepository;
import com.rkc.zds.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public UserDto findByUserName(String userName) {
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

	@Override
	public UserDto registerNewUserAccount(final UserDto accountDto) {
		if (loginExist(accountDto.getLogin())) {
			throw new UserAlreadyExistException("There is an account with that username: " + accountDto.getLogin());
		}
		
		//final User user = new User();

		//user.setFirstName(accountDto.getFirstName());
		//user.setLastName(accountDto.getLastName());
		accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		//user.setEmail(accountDto.getEmail());
		//user.setUsing2FA(accountDto.isUsing2FA());
		UserDto user = userRepository.save(accountDto);
		
		AuthorityDto role = new AuthorityDto();
		role.setUsername(accountDto.getLogin());
		role.setAuthority("ROLE_USER");
		
		authorityRepository.save(role);
		
		return user;


	}

	private boolean loginExist(final String login) {

		UserDto user = userRepository.findByLogin(login);
		if (user != null) {

			return true;
		}
		
		return false;
	}
}
