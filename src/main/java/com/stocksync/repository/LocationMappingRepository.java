package com.stocksync.repository;

import com.stocksync.entity.LocationMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LocationMappingRepository extends JpaRepository<LocationMapping, UUID> {
    List<LocationMapping> findByItemId(UUID itemId);
    List<LocationMapping> findByLocationId(UUID locationId);
}
