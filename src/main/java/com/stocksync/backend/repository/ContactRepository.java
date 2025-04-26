package com.stocksync.backend.repository;

import com.stocksync.backend.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findByType(Integer type);

    // contactRepository.findById(id) is a method provided by JpaRepository.
    // It retrieves an Optional<Contact> by its primary key (UUID id).
    // If a Contact with the given id exists, the Optional contains the Contact entity; otherwise, it is empty.

    // Example usage in service:
    // Optional<Contact> opt = contactRepository.findById(id);
}
