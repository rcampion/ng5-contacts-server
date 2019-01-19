package com.rkc.zds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rkc.zds.dto.UserContactsDto;
import com.rkc.zds.repository.UserContactsRepository;
import com.rkc.zds.service.UserContactsService;

@Service
public class UserContactsServiceImpl implements UserContactsService {

	@Autowired
	private UserContactsRepository userContactsRepo;

	@Override
	public Page<UserContactsDto> findUserContacts(Pageable pageable, int id) {

		Page<UserContactsDto> page = userContactsRepo.findByUserId(pageable, id);

		return page;
	}

	@Override
	public List<UserContactsDto> findAllUserContacts(int userId) {

		List<UserContactsDto> list = userContactsRepo.findByUserId(userId);

		return list;
	}
	
	private Sort sortByIdASC() {
		return new Sort(Sort.Direction.ASC, "userId");
	}

	@Override
	public void saveUserContact(UserContactsDto userContact) {
		// checking for duplicates
		List<UserContactsDto> list = userContactsRepo.findByUserId(userContact.getUserId());

		//return if duplicate found
		for (UserContactsDto element : list) {
			if (element.getContactId() == userContact.getContactId()) {
				return;
			}
		}

		userContactsRepo.save(userContact);
	}

	@Override
	public void deleteUserContact(int id) {

		userContactsRepo.deleteById(id);
		
	}	
}
