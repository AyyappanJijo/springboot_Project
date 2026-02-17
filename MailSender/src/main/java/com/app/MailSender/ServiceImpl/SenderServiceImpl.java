package com.app.MailSender.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.MailSender.Entity.Sender;
import com.app.MailSender.Repository.SenderRepository;
import com.app.MailSender.Service.SenderService;

@Service
public class SenderServiceImpl implements SenderService{
	
	@Autowired
	private SenderRepository senderRepo;

	@Override
	public Sender createSender(Sender sender) {
		sender.setCreatedAt(LocalDateTime.now());
		return senderRepo.save(sender);
	}

	@Override
	public List<Sender> getAllSender() {
		return senderRepo.findAll();
	}
	

}
