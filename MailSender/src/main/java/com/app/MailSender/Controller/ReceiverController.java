package com.app.MailSender.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.MailSender.Entity.Receiver;
import com.app.MailSender.Service.ReceiverService;

@RestController
@RequestMapping("/api/receiver")
public class ReceiverController {
	
	@Autowired
	private ReceiverService receiverService;
	
	@PostMapping
	public Receiver createReceiver(@RequestBody Receiver receiver) {
		return receiverService.createReceiver(receiver);
	}
	
	@GetMapping
	public List<Receiver> getAllReceiver(){
		return receiverService.getAllReceiver();
	}

}
