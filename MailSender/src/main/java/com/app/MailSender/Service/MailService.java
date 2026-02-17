package com.app.MailSender.Service;

import java.util.List;

import com.app.MailSender.Dto.MailRequestDto;
import com.app.MailSender.Entity.Mail;

public interface MailService {
	
	Mail sendMail(MailRequestDto dto);
	List<Mail> getAllMail();
	Mail updateMail(Long id, MailRequestDto dto);
	void deleteMail(Long id);
	List<Mail> getInbox(Long receiverId);

}