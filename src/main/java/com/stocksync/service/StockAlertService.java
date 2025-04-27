package com.stocksync.service;

import com.stocksync.entity.StockAlert;
import com.stocksync.entity.LocationMapping;
import com.stocksync.repository.StockAlertRepository;
import com.stocksync.repository.ItemRepository;
import com.stocksync.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StockAlertService {
    @Autowired
    private StockAlertRepository stockAlertRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LocationRepository locationRepository;

    public void checkAndCreateAlert(LocationMapping mapping) {
        if (mapping.getAvailableQuantity() <= mapping.getMinimumQuantity()) {
            StockAlert alert = new StockAlert();

            // Get Item and Location entities
            itemRepository.findById(mapping.getItemId()).ifPresent(alert::setItem);
            locationRepository.findById(mapping.getLocationId()).ifPresent(alert::setLocation);

            alert.setCurrentQuantity(mapping.getAvailableQuantity());
            alert.setMinimumQuantity(mapping.getMinimumQuantity());
            alert.setCreatedTime(LocalDateTime.now());
            alert.setRead(false);
            stockAlertRepository.save(alert);
        }
    }

    public List<StockAlert> getUnreadAlerts() {
        return stockAlertRepository.findByIsReadOrderByCreatedTimeDesc(false);
    }

    public void markAsRead(UUID alertId) {
        stockAlertRepository.findById(alertId).ifPresent(alert -> {
            alert.setRead(true);
            stockAlertRepository.save(alert);
        });
    }
}
