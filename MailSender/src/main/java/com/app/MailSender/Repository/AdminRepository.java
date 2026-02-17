package com.app.MailSender.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.MailSender.Entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
