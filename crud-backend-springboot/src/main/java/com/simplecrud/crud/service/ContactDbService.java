package com.simplecrud.crud.service;


import com.simplecrud.crud.model.ContactDb;

import java.util.List;

public interface ContactDbService {

    List<ContactDb> getAllContactDb();
    ContactDb save(ContactDb contactDb);
    ContactDb findById(int contactDbId);
    void deleteById(int contactDbId);

    ContactDb update(int contactDbId, ContactDb contactDb);
//    public String createContactDb(ContactDb contactDb);
//    public String updateContactDb(ContactDb contactDb);
//    public String deleteContactDb(String contactDbId);
//    public ContactDb getContactDb(String contactDbId);
//    public List<ContactDb> getAllContactDb();
}