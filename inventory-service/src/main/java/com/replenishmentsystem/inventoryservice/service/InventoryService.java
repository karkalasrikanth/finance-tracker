package com.replenishmentsystem.inventoryservice.service;

import com.replenishmentsystem.inventoryservice.entity.Inventory;
import com.replenishmentsystem.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

}
