package com.simplecrud.crud.repository;

import com.simplecrud.crud.model.ContactDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactDbRepository extends JpaRepository<ContactDb, Integer> {
}