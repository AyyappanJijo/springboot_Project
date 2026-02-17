package com.app.MailSender.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.MailSender.Dto.MailRequestDto;
import com.app.MailSender.Entity.Mail;
import com.app.MailSender.Entity.Receiver;
import com.app.MailSender.Entity.Sender;
import com.app.MailSender.Exception.ResourceNotFoundException;
import com.app.MailSender.Repository.MailRepository;
import com.app.MailSender.Repository.ReceiverRepository;
import com.app.MailSender.Repository.SenderRepository;
import com.app.MailSender.Service.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private MailRepository mailRepo;
	
	@Autowired
	private SenderRepository senderRepo;
	
	@Autowired
	private ReceiverRepository receiverRepo;

	@Override
	public Mail sendMail(MailRequestDto dto) {
		Sender sender = senderRepo.findById(dto.getSenderId())
				.orElseThrow(()-> new ResourceNotFoundException("Sender Not Found"));
		
		Receiver receiver = receiverRepo.findById(dto.getReceiverId())
				.orElseThrow(()-> new ResourceNotFoundException("Receiver Not Found"));
		
		Mail mail = new Mail();
		mail.setSubject(dto.getSubject());
		mail.setMessage(dto.getMessage());
		mail.setCc(dto.getCc());
		mail.setBcc(dto.getBcc());
		mail.setSender(sender);
		mail.setReceiver(receiver);
		mail.setCreatedAt(LocalDateTime.now());
		return mailRepo.save(mail);
	}

	@Override
	public List<Mail> getAllMail() {
		return mailRepo.findAll();
	}
	
	@Override
	public List<Mail> getInbox(Long receiverId){
		return mailRepo.findByReceiverReceiverId(receiverId);
	}

	@Override
	public Mail updateMail(Long id, MailRequestDto dto) {
		
		if(id == null) {
			throw new IllegalArgumentException("MailId Must Not be Null");
		}
		Mail mail = mailRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Mail Not Found"));
		
		mail.setSubject(dto.getSubject());
		mail.setMessage(dto.getMessage());
		mail.setCc(dto.getCc());
		mail.setBcc(dto.getBcc());
		return mailRepo.save(mail);
	}

	@Override
	public void deleteMail(Long id) {
		Mail mail = mailRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Mail Not Found"));
		mailRepo.delete(mail);
		
	}

	
}
