package com.replenishmentsystem.inventoryservice.controller;

import com.replenishmentsystem.inventoryservice.entity.Inventory;
import com.replenishmentsystem.inventoryservice.service.ReplenishmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/replenishment")
public class ReplenishmentController {

    private final ReplenishmentService replenishmentService;

    public ReplenishmentController(ReplenishmentService replenishmentService) {
        this.replenishmentService = replenishmentService;
    }

    @PostMapping("/check")
    public boolean check(@RequestBody Inventory inventory) {
        return replenishmentService.shouldReorder(inventory.getStock(), inventory.getReorderPoint());
    }
}
