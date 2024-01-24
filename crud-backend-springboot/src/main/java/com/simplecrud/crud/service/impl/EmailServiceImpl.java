package com.simplecrud.crud.service.impl;

import com.simplecrud.crud.exception.ContactDbNotFoundException;
import com.simplecrud.crud.model.ContactDb;
import com.simplecrud.crud.model.Mailing;
import com.simplecrud.crud.repository.ContactDbRepository;
import com.simplecrud.crud.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    ContactDbRepository contactDbRepository;

    @Override
    public void sendMail(Mailing mailing) {
        ContactDb contactDb = new ContactDb(contactDbRepository.findById(mailing.getId())
                .orElseThrow(() -> new ContactDbNotFoundException(mailing.getId())));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailing.getEmailTo());
        message.setSubject("Send Email by Id");
        message.setText(contactDb.toString());

        mailSender.send(message);
    }
}
