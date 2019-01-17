package com.rkc.zds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rkc.zds.dto.AuthorityDto;

public interface AuthorityRepository extends JpaRepository<AuthorityDto, Integer> {
	
	AuthorityDto findByUserName(String userName);
	
}
