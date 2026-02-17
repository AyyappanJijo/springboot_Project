package com.app.MailSender.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.MailSender.Entity.Receiver;
import com.app.MailSender.Repository.ReceiverRepository;
import com.app.MailSender.Service.ReceiverService;

@Service
public class ReceiverServiceImpl implements ReceiverService{
	
	@Autowired
	private ReceiverRepository receiverRepo;

	@Override
	public Receiver createReceiver(Receiver receiver) {
		return receiverRepo.save(receiver);
	}

	@Override
	public List<Receiver> getAllReceiver() {
		return receiverRepo.findAll();
	}
	

}
