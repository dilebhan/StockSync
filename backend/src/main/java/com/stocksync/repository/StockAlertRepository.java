package com.stocksync.backend.repository;

import com.stocksync.backend.entity.StockAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface StockAlertRepository extends JpaRepository<StockAlert, UUID> {
    List<StockAlert> findByIsReadOrderByCreatedTimeDesc(boolean isRead);
    List<StockAlert> findByItem_ItemIdOrderByCreatedTimeDesc(UUID itemId);
}
