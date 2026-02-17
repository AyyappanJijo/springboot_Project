package com.app.MailSender.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.MailSender.Entity.Inbox;

@Repository
public interface InboxRepository extends JpaRepository<Inbox, Long>{

}
