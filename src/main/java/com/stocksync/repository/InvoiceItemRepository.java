package com.stocksync.repository;

import com.stocksync.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, UUID> {
    List<InvoiceItem> findByInvoice_InvoiceId(UUID invoiceId);
    void deleteByInvoice_InvoiceId(UUID invoiceId);
}
