package com.stocksync.repository;

import com.stocksync.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BillItemRepository extends JpaRepository<BillItem, UUID> {
    List<BillItem> findByBill_BillId(UUID billId);
}
