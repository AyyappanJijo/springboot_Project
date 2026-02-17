package com.app.MailSender.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.MailSender.Dto.MailRequestDto;
import com.app.MailSender.Entity.Mail;
import com.app.MailSender.Service.MailService;

@RestController
@RequestMapping("/api/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@PostMapping
	public Mail sendMail(@RequestBody MailRequestDto dto) {
		return mailService.sendMail(dto);
	}
	
	@GetMapping
	public List<Mail> getAllMails(){
		return mailService.getAllMail();
	}
	
	@GetMapping("/inbox/{receiverId}")
	public List<Mail> inbox(@PathVariable Long receiverId){
		return mailService.getInbox(receiverId);
	}
	
	@PutMapping("/{id}/update")
	public Mail updateMail(@PathVariable Long id, @RequestBody MailRequestDto dto) {
		return mailService.updateMail(id, dto);
	}
	
	@DeleteMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		mailService.deleteMail(id);
		return "Mail Deleted Successfully";
	}
	

}
