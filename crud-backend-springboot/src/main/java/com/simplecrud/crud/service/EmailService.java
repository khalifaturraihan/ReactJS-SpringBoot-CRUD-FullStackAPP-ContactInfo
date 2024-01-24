package com.simplecrud.crud.service;

import com.simplecrud.crud.model.Mailing;

public interface EmailService {
    void sendMail(Mailing mailing);
}
