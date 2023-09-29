package com.kiu.lims.controller;

import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/lims/inventory-managemment")
public class InventoryContoller {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/get-all")
    public ResponseModel getAllInventoryManagement(){
        return  inventoryService.getAllInventoryManagement();
    }

}
