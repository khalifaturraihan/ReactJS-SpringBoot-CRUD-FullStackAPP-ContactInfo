package com.simplecrud.crud.controller;

import com.simplecrud.crud.model.ContactDb;
import com.simplecrud.crud.model.Mailing;

import com.simplecrud.crud.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.simplecrud.crud.model.ContactDb;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send-mail")
public class MailingController {
    @Autowired
    private EmailService emailService;
    ContactDb contactDb;

    @PostMapping
    public String sendEmailContactDbDetails(@RequestBody @Validated Mailing mailing)
    {
        // Kirim email
        emailService.sendMail(mailing);

        return "Contact Send Email Successfully";
    }
}