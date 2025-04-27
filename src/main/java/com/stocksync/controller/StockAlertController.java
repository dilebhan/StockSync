package com.stocksync.controller;

import com.stocksync.entity.StockAlert;
import com.stocksync.service.StockAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stock-alerts")
public class StockAlertController {
    @Autowired
    private StockAlertService stockAlertService;

    @GetMapping("/unread")
    public ResponseEntity<List<StockAlert>> getUnreadAlerts() {
        return ResponseEntity.ok(stockAlertService.getUnreadAlerts());
    }

    @PostMapping("/{alertId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable UUID alertId) {
        stockAlertService.markAsRead(alertId);
        return ResponseEntity.ok().build();
    }
}
