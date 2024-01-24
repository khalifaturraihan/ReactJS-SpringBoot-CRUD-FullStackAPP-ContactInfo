package com.simplecrud.crud.service.impl;

import com.simplecrud.crud.model.ContactDb;
import com.simplecrud.crud.repository.ContactDbRepository;
import com.simplecrud.crud.service.ContactDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactDbServiceImpl implements ContactDbService {

    ContactDbRepository contactDbRepository;

    public ContactDbServiceImpl(ContactDbRepository contactDbRepository) {
        this.contactDbRepository = contactDbRepository;
    }
//    @Override
//    public String createContactDb(ContactDb contactDb) {
//        // More Business Logic
//        contactDbRepository.save(contactDb);
//        return "Success";
//    }
//
//    @Override
//    public String updateContactDb(ContactDb contactDb) {
//        // More Business Logic
//        contactDbRepository.save(contactDb);
//        return "Success";
//    }

//    @Override
//    public String deleteContactDb(String contactDbId) {
//        // More Business Logic
//        contactDbRepository.deleteById(contactDbId);
//        return "Success";
//    }

//    @Override
//    public ContactDb getContactDb(String contactDbId) {
//        // More Business Logic
//        return contactDbRepository.findById(contactDbId).get();
//    }

    @Override
    public List<ContactDb> getAllContactDb() {
        // More Business Logic
        return contactDbRepository.findAll();
    }

    @Override
    public ContactDb save(ContactDb contactDb) {
        return contactDbRepository.save(contactDb);
    }


    @Override
    public ContactDb findById(int contactDbId) {
        Optional<ContactDb> result = contactDbRepository.findById(contactDbId);

        ContactDb contactDb = null;

        if (result.isPresent()) {
            contactDb = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find contact id - " + contactDbId);
        }

        return contactDb;
    }

    @Override
    public void deleteById(int contactDbId) {
        contactDbRepository.deleteById(contactDbId);
    }

    @Override
    public ContactDb update(int contactDbId, ContactDb contactDb) {
        contactDbRepository.findById(contactDbId).orElseThrow(() -> new RuntimeException("Did not find contact id - " + contactDbId));
        contactDb.setContactId(contactDbId);
        return contactDbRepository.save(contactDb);
    }
}