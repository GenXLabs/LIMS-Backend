package com.kiu.lims.service.impl;

import com.kiu.lims.entity.InventoryEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.InventoryRepository;
import com.kiu.lims.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryManagementImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryEntity> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public InventoryEntity getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public InventoryEntity createInventory(InventoryEntity inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public InventoryEntity updateInventoryById(Long id, InventoryEntity updatedInventory) {
        // Check if the inventory item exists
        if (inventoryRepository.existsById(id)) {
            // Set the ID of the updatedInventory to match the ID in the path
            updatedInventory.setInventoryid(id);
            return inventoryRepository.save(updatedInventory);
        } else {
            return null; // Return null or throw an exception to indicate item not found
        }
    }


    @Override
    public boolean deleteInventoryById(Long id) {
        // Check if the inventory item exists
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return true;
        } else {
            return false; // Return false or throw an exception to indicate item not found
        }
    }
}
