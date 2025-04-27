package com.stocksync.controller;

import com.stocksync.dto.ItemDTO;
import com.stocksync.entity.Item;
import com.stocksync.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ItemDTO dto) {
        Item item = itemService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody ItemDTO dto) {
        return itemService.update(id, dto)
            .<ResponseEntity<?>>map(item -> ResponseEntity.ok().body(item))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (itemService.delete(id)) {
            return ResponseEntity.ok().body("Item deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id) {
        return itemService.get(id)
            .<ResponseEntity<?>>map(item -> ResponseEntity.ok().body(item))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found"));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(itemService.getAll());
    }
}
