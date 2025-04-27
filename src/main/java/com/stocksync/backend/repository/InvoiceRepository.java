package com.stocksync.backend.repository;

import com.stocksync.backend.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    // ...existing code...
}
