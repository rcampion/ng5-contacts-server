package com.rkc.zds.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rkc.zds.dto.ContactDto;
import com.rkc.zds.dto.EMailDto;
import com.rkc.zds.repository.EMailRepository;
import com.rkc.zds.service.EMailService;

@Service
public class EMailServiceImpl implements EMailService {

	@Autowired
	private EMailRepository eMailRepo;

	@Override
	public Page<EMailDto> findEMails(Pageable pageable, int contactId) {

		Page<EMailDto> page = eMailRepo.findByContactId(pageable, contactId);

		return page;
	}

	@Override
	public void saveEMail(EMailDto email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEMail(int id) {
		eMailRepo.deleteById(id);

	}

	@Override
	public EMailDto getEMail(int id) {
	
		Optional<EMailDto> email = eMailRepo.findById(id);
		if(email.isPresent())
			return email.get();
		else
			return null;
	}

}
