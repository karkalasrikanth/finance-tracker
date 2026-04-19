package com.replenishmentsystem.inventoryservice.repository;

import com.replenishmentsystem.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
