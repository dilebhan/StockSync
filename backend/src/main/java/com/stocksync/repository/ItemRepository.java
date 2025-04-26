package com.stocksync.backend.repository;

import com.stocksync.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    // ...existing code...
}
