package com.stocksync.backend.controller;

import com.stocksync.backend.dto.BillDTO;
import com.stocksync.backend.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping
    public ResponseEntity<BillDTO> createBill(@RequestBody BillDTO dto) {
        return ResponseEntity.ok(billService.createBill(dto));
    }

    @PutMapping("/{billId}")
    public ResponseEntity<BillDTO> updateBill(@PathVariable UUID billId, @RequestBody BillDTO dto) {
        return billService.updateBill(billId, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable UUID billId) {
        if (billService.deleteBill(billId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillDTO> getBill(@PathVariable UUID billId) {
        return billService.getBill(billId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<BillDTO> listBills() {
        return billService.listBills();
    }
}
