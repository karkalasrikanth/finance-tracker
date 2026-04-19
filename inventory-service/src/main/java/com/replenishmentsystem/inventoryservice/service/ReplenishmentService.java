package com.replenishmentsystem.inventoryservice.service;

import org.springframework.stereotype.Service;

@Service
public class ReplenishmentService {
    public int calculateReorderPoint(int demand, int leadTime, int safetyStock) {
        return (demand * leadTime) + safetyStock;
    }

    public boolean shouldReorder(int stock, int reorderPoint) {
        return stock <= reorderPoint;
    }
}
