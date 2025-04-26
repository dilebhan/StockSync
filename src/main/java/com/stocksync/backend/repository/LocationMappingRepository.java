package com.stocksync.backend.repository;

import com.stocksync.backend.entity.LocationMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LocationMappingRepository extends JpaRepository<LocationMapping, UUID> {
    List<LocationMapping> findByItemId(UUID itemId);
    void deleteByItemId(UUID itemId);
}
