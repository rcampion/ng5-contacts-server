package com.rkc.zds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.rkc.zds.dto.ContactDto;
import com.rkc.zds.dto.UserDto;

public interface UserRepository extends JpaRepository<UserDto, Integer>, JpaSpecificationExecutor<UserDto>{
	UserDto findByUserName(String userName);

	UserDto findByLogin(String login);
}
