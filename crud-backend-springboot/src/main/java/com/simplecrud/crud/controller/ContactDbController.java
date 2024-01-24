package com.simplecrud.crud.controller;

import com.simplecrud.crud.model.ContactDb;
//import com.simplecrud.crud.response.ResponseHandler;
import com.simplecrud.crud.service.ContactDbService;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ContactDbController
{
    ContactDbService contactDbService;
    public ContactDbController(ContactDbService contactDbService) {
        this.contactDbService = contactDbService;
    }

    // Read Specific Cloud Vendor Details from DB
//    @GetMapping("/{contactId}")
//    @ApiOperation(value ="Contact id", notes="Provide contact details",
//            response = ResponseEntity.class)
//    public ResponseEntity<Object> getContactDbDetails(@PathVariable("contactId") String contactId)
//    {
//        return ResponseHandler.responseBuilder("Requested Contact Details are given here",
//                HttpStatus.OK, contactDbService.getContactDb(contactId));
//    }
    // Read Specific Contact Details from DB
    @GetMapping("/contact/{contactId}")
    public ContactDb getContactDbDetails(@PathVariable("contactId") int contactId)
    {
        ContactDb contactDb = contactDbService.findById(contactId);

        if (contactDb == null) {
            throw new RuntimeException("Contact id not found - " + contactId);
        }

        return contactDb;
    }

    // Read All Contact Details from DB
    @GetMapping("/contact")
    public List<ContactDb> getAllContactDbDetails()
    {
        return contactDbService.getAllContactDb();
    }

    @PostMapping("/contact")
    public ContactDb createContactDbDetails(@RequestBody ContactDb contactDb)
    {
        // Simpan kontak
//        contactDbService.createContactDb(contactDb);
//
//        return "Contact Created Successfully";
        contactDb.setContactId(0);

        ContactDb dbContact = contactDbService.save(contactDb);

        return dbContact;
    }

    @PutMapping("/contact/{contactId}")
    public ContactDb updateContactDbDetails(@PathVariable("contactId") int contactId, @RequestBody ContactDb contactDb)
    {
        ContactDb dbContact = contactDbService.update(contactId, contactDb);

        return dbContact;
    }

    @DeleteMapping("/contact/{contactId}")
    public String deleteContactDbDetails(@PathVariable("contactId") int contactId)
    {
        ContactDb tempContact = contactDbService.findById(contactId);

        // throw exception if null

        if (tempContact == null) {
            throw new RuntimeException("Contact id not found - " + contactId);
        }

        contactDbService.deleteById(contactId);

        return "Deleted employee id - " + contactId;
    }
}
