package com.stocksync.backend.repository;

import com.stocksync.backend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
    // ...existing code...
}
