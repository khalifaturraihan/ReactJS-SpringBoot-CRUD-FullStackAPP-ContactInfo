package com.simplecrud.crud.exception;

public class ContactDbNotFoundException extends RuntimeException {

        public ContactDbNotFoundException(int id) {
            super("Contact not found with ID: " + id);
        }
}
