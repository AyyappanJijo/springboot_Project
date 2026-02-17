package com.app.MailSender.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.MailSender.Entity.Receiver;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long>{

}
