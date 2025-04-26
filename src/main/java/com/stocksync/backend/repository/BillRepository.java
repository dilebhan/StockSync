package com.stocksync.backend.repository;

import com.stocksync.backend.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {
    // ...existing code...
}
