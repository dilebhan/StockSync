package com.stocksync.backend.controller;

import com.stocksync.backend.dto.ContactDTO;
import com.stocksync.backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Customer endpoints (Type = 1)
    @PostMapping("/customer")
    public ResponseEntity<ContactDTO> createCustomer(@RequestBody ContactDTO dto) {
        return ResponseEntity.ok(contactService.createContact(dto, 1));
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<ContactDTO> updateCustomer(@PathVariable UUID id, @RequestBody ContactDTO dto) {
        return contactService.updateContact(id, dto, 1)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        if (contactService.deleteContact(id, 1)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ContactDTO> getCustomer(@PathVariable UUID id) {
        return contactService.getContact(id, 1)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customers")
    public List<ContactDTO> listCustomers() {
        return contactService.listContacts(1);
    }

    // Vendor endpoints (Type = 2)
    @PostMapping("/vendor")
    public ResponseEntity<ContactDTO> createVendor(@RequestBody ContactDTO dto) {
        return ResponseEntity.ok(contactService.createContact(dto, 2));
    }

    @PutMapping("/vendor/{id}")
    public ResponseEntity<ContactDTO> updateVendor(@PathVariable UUID id, @RequestBody ContactDTO dto) {
        return contactService.updateContact(id, dto, 2)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/vendor/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable UUID id) {
        if (contactService.deleteContact(id, 2)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/vendor/{id}")
    public ResponseEntity<ContactDTO> getVendor(@PathVariable UUID id) {
        return contactService.getContact(id, 2)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vendors")
    public List<ContactDTO> listVendors() {
        return contactService.listContacts(2);
    }
}
