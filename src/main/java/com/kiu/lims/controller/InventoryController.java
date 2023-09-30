package com.kiu.lims.controller;

import com.kiu.lims.entity.InventoryEntity;
import com.kiu.lims.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lims/inventory-management") // Corrected the endpoint path
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/get-all")
    public ResponseEntity<List<InventoryEntity>> getAllInventory() {
        List<InventoryEntity> inventoryList = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventoryList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InventoryEntity> getInventoryById(@PathVariable Long id) {
        InventoryEntity inventory = inventoryService.getInventoryById(id);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<InventoryEntity> createInventory(@RequestBody InventoryEntity inventory) {
        InventoryEntity createdInventory = inventoryService.createInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InventoryEntity> updateInventoryById(@PathVariable Long id, @RequestBody InventoryEntity updatedInventory) {
        InventoryEntity inventory = inventoryService.updateInventoryById(id, updatedInventory);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInventoryById(@PathVariable Long id) {
        if (inventoryService.deleteInventoryById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
