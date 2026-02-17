package com.app.MailSender.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.MailSender.Entity.Sender;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {

}
