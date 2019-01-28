package com.rkc.zds.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.rkc.zds.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	public Page<UserDto> findUsers(Pageable pageable) {

		return userRepository.findAll(pageable);
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
	public UserDto getUser(int id) {
	
		Optional<UserDto> user = userRepository.findById(id);
		if(user.isPresent())
			return user.get();
		else
			return null;
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
		
		accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		accountDto.setEnabled(1);
		UserDto user = userRepository.save(accountDto);
		
		AuthorityDto role = new AuthorityDto();
		role.setUserName(accountDto.getLogin());
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
	
	@Override
	public Page<UserDto> searchUsers(Pageable pageable, Specification<UserDto> spec) {
		return userRepository.findAll(spec, pageable);
	}
}
