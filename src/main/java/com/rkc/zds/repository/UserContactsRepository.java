package com.rkc.zds.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rkc.zds.dto.UserContactsDto;

public interface UserContactsRepository extends JpaRepository<UserContactsDto, Integer> {
  
	Page<UserContactsDto> findByUserId(Pageable pageable, int userId);

	List<UserContactsDto> findByUserId(int userId);
	       
}