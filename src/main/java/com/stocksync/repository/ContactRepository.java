package com.stocksync.repository;

import com.stocksync.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findByType(Integer type);
}

