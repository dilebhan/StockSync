package com.stocksync.service;

import com.stocksync.entity.Contact;
import com.stocksync.dto.ContactDTO;
import com.stocksync.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact createContact(ContactDTO dto, int type) {
        Contact contact = new Contact();
        contact.setContactName(dto.getContactName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setCountry(dto.getCountry());
        contact.setZipCode(dto.getZipCode());
        contact.setType(type);
        long now = System.currentTimeMillis();
        contact.setCreatedTime(now);
        contact.setUpdatedTime(now);
        return contactRepository.save(contact);
    }

    public Optional<Contact> updateContact(UUID id, ContactDTO dto, int type) {
        Optional<Contact> optional = contactRepository.findById(id);
        if (optional.isPresent()) {
            Contact contact = optional.get();
            contact.setContactName(dto.getContactName());
            contact.setEmail(dto.getEmail());
            contact.setPhone(dto.getPhone());
            contact.setAddress(dto.getAddress());
            contact.setCity(dto.getCity());
            contact.setState(dto.getState());
            contact.setCountry(dto.getCountry());
            contact.setZipCode(dto.getZipCode());
            contact.setType(type);
            contact.setUpdatedTime(System.currentTimeMillis());
            contactRepository.save(contact);
            return Optional.of(contact);
        }
        return Optional.empty();
    }

    public boolean deleteContact(UUID id, int type) {
        Optional<Contact> optional = contactRepository.findById(id);
        if (optional.isPresent() && optional.get().getType() == type) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Contact> getContactById(UUID id, int type) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.filter(c -> c.getType() == type);
    }

    public List<Contact> getContactsByType(int type) {
        return contactRepository.findAll()
            .stream()
            .filter(c -> c.getType() != null && c.getType() == type)
            .toList();
    }
}
