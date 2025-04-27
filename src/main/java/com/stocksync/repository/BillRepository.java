package com.stocksync.repository;

import com.stocksync.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {
}
