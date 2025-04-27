package com.stocksync.backend.service;

import com.stocksync.backend.dto.ContactDTO;
import com.stocksync.backend.entity.Contact;
import com.stocksync.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private ContactDTO toDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setContactId(contact.getContactId());
        dto.setName(contact.getName());
        dto.setMobile(contact.getMobile());
        dto.setEmail(contact.getEmail());
        dto.setAddress(contact.getAddress());
        return dto;
    }

    private void updateEntity(Contact contact, ContactDTO dto) {
        contact.setName(dto.getName());
        contact.setMobile(dto.getMobile());
        contact.setEmail(dto.getEmail());
        contact.setAddress(dto.getAddress());
    }

    public ContactDTO createContact(ContactDTO dto, int type) {
        Contact contact = new Contact();
        contact.setType(type);
        updateEntity(contact, dto);
        long now = Instant.now().toEpochMilli();
        contact.setCreatedTime(now);
        contact.setUpdatedTime(now);
        contact = contactRepository.save(contact); // save usage
        return toDTO(contact);
    }

    public Optional<ContactDTO> updateContact(UUID id, ContactDTO dto, int type) {
        Optional<Contact> opt = contactRepository.findById(id); // findById usage
        if (opt.isPresent() && opt.get().getType() == type) {
            Contact contact = opt.get();
            updateEntity(contact, dto);
            contact.setUpdatedTime(Instant.now().toEpochMilli());
            contact = contactRepository.save(contact); // save usage
            return Optional.of(toDTO(contact));
        }
        return Optional.empty();
    }

    public boolean deleteContact(UUID id, int type) {
        Optional<Contact> opt = contactRepository.findById(id);
        if (opt.isPresent() && opt.get().getType() == type) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ContactDTO> getContact(UUID id, int type) {
        Optional<Contact> opt = contactRepository.findById(id);
        if (opt.isPresent() && opt.get().getType() == type) {
            return Optional.of(toDTO(opt.get()));
        }
        return Optional.empty();
    }

    public List<ContactDTO> listContacts(int type) {
        return contactRepository.findByType(type)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
