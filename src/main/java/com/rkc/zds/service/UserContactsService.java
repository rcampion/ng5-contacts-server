package com.rkc.zds.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.rkc.zds.dto.UserContactsDto;

public interface UserContactsService {
    Page<UserContactsDto> findUserContacts(Pageable pageable, int userId);
    
    List<UserContactsDto> findAllUserContacts(int userId);

    @Transactional    
    public void saveUserContact(UserContactsDto userContact);    

    @Transactional  
	void deleteUserContact(int id);
}
