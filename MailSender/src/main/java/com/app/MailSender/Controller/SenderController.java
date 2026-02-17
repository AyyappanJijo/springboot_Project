package com.app.MailSender.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.MailSender.Entity.Sender;
import com.app.MailSender.Service.SenderService;

@RestController
@RequestMapping("/api/sender")
public class SenderController {
	
	@Autowired
	private SenderService senderService;
	
	@PostMapping
	public Sender createSender(@RequestBody Sender sender) {
		return senderService.createSender(sender);
	}
	
	@GetMapping
	public List<Sender> getAllSender(){
		return senderService.getAllSender();
	}

}
