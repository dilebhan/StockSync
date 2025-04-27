package com.stocksync.controller;

import com.stocksync.dto.ContactDTO;
import com.stocksync.entity.Contact;
import com.stocksync.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody ContactDTO dto) {
        Contact contact = contactService.createContact(dto, 1);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }

    @PostMapping("/vendor")
    public ResponseEntity<?> createVendor(@Valid @RequestBody ContactDTO dto) {
        Contact contact = contactService.createContact(dto, 2);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable UUID id, @Valid @RequestBody ContactDTO dto) {
        Optional<Contact> updated = contactService.updateContact(id, dto, 1);
        return updated
            .<ResponseEntity<?>>map(contact -> ResponseEntity.ok().body(contact))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found"));
    }

    @PutMapping("/vendor/{id}")
    public ResponseEntity<?> updateVendor(@PathVariable UUID id, @Valid @RequestBody ContactDTO dto) {
        Optional<Contact> updated = contactService.updateContact(id, dto, 2);
        return updated
            .<ResponseEntity<?>>map(contact -> ResponseEntity.ok().body(contact))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendor not found"));
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
        boolean deleted = contactService.deleteContact(id, 1);
        if (deleted) {
            return ResponseEntity.ok().body("Customer deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }

    @DeleteMapping("/vendor/{id}")
    public ResponseEntity<?> deleteVendor(@PathVariable UUID id) {
        boolean deleted = contactService.deleteContact(id, 2);
        if (deleted) {
            return ResponseEntity.ok().body("Vendor deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendor not found");
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable UUID id) {
        return contactService.getContactById(id, 1)
            .<ResponseEntity<?>>map(contact -> ResponseEntity.ok().body(contact))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found"));
    }

    @GetMapping("/vendor/{id}")
    public ResponseEntity<?> getVendor(@PathVariable UUID id) {
        return contactService.getContactById(id, 2)
            .<ResponseEntity<?>>map(contact -> ResponseEntity.ok().body(contact))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendor not found"));
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(contactService.getContactsByType(1));
    }

    @GetMapping("/vendors")
    public ResponseEntity<?> getAllVendors() {
        return ResponseEntity.ok(contactService.getContactsByType(2));
    }
}
