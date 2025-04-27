package com.stocksync.controller;

import com.stocksync.dto.BillDTO;
import com.stocksync.entity.Bill;
import com.stocksync.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BillDTO dto) {
        Bill bill = billService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @Valid @RequestBody BillDTO dto) {
        return billService.update(id, dto)
            .<ResponseEntity<?>>map(bill -> ResponseEntity.ok().body(bill))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bill not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        if (billService.delete(id)) {
            return ResponseEntity.ok().body("Bill deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bill not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") UUID id) {
        return billService.get(id)
            .<ResponseEntity<?>>map(bill -> ResponseEntity.ok().body(bill))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bill not found"));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(billService.getAll());
    }
}
