package com.app.MailSender.Service;

import java.util.List;

import com.app.MailSender.Entity.Receiver;

public interface ReceiverService {
	
	Receiver createReceiver(Receiver receiver);
	List<Receiver> getAllReceiver();
}
