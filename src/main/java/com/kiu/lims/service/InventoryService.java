package com.kiu.lims.service;

import com.kiu.lims.entity.InventoryEntity;

import java.util.List;

public interface InventoryService {

    List<InventoryEntity> getAllInventory();

    InventoryEntity getInventoryById(Long id);

    InventoryEntity createInventory(InventoryEntity inventory);

    InventoryEntity updateInventoryById(Long id, InventoryEntity updatedInventory);


    boolean deleteInventoryById(Long id);
}
