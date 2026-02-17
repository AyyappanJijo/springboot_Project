package com.app.MailSender.Service;

import java.util.List;

import com.app.MailSender.Entity.Sender;

public interface SenderService {
	
	Sender createSender(Sender sender);
	List<Sender> getAllSender();

}
