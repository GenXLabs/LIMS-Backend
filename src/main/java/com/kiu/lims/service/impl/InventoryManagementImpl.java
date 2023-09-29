package com.kiu.lims.service.impl;

import com.kiu.lims.entity.InventoryEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.InventoryRepository;
import com.kiu.lims.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryManagementImpl implements InventoryService {


    private final InventoryRepository inventoryRepository;

    @Override
    public ResponseModel getAllInventoryManagement()
    {
        ResponseModel responseModel = new ResponseModel();
        List<InventoryEntity> inventoryEntityList;

        try{
            inventoryEntityList=inventoryRepository.findAll();
            List
        }

        return null;
    }

}

