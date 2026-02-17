package com.app.MailSender.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.MailSender.Entity.Mail;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
	
	List<Mail> findByReceiverReceiverId(Long receiverId);

}
