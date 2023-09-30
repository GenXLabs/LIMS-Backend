package com.kiu.lims.service.impl;

import com.kiu.lims.entity.InternalQualityAssuranceEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.InternalQualityAssuranceRepository;
import com.kiu.lims.service.InternalQualityAssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternalQualityAssuranceServiceImpl implements InternalQualityAssuranceService {

    @Autowired
    private InternalQualityAssuranceRepository internalQualityAssuranceRepository;

    @Override
    public ResponseModel getAllInternalQualityAssurance() {
        ResponseModel responseModel = new ResponseModel();
        List<InternalQualityAssuranceEntity> internalQualityAssuranceEntityList;

        try {
            internalQualityAssuranceEntityList = internalQualityAssuranceRepository.findAll();

            if (internalQualityAssuranceEntityList.isEmpty()) {
                responseModel.setCode(204);
                responseModel.setMessage("No Internal Quality Assurance found");
            } else {
                responseModel.setCode(200);
                responseModel.setMessage("Successful");
                responseModel.setData(internalQualityAssuranceEntityList);
            }
        } catch (Exception e) {
            responseModel.setCode(500);
            responseModel.setMessage("Internal Server Error");
        }
        return responseModel;
    }

    @Override
    public ResponseModel createInternalQualityAssurance(InternalQualityAssuranceEntity newEntity) {
        ResponseModel responseModel = new ResponseModel();

        try {
            // Save the new entity to the database
            InternalQualityAssuranceEntity savedEntity = internalQualityAssuranceRepository.save(newEntity);

            responseModel.setCode(201); // 201 Created
            responseModel.setMessage("Internal Quality Assurance created successfully");
            responseModel.setData(savedEntity);
        } catch (Exception e) {
            responseModel.setCode(500); // 500 Internal Server Error
            responseModel.setMessage("Failed to create Internal Quality Assurance");
        }

        return responseModel;
    }

    @Override
    public ResponseModel updateInternalQualityAssurance(Long id, InternalQualityAssuranceEntity updatedEntity) {
        ResponseModel responseModel = new ResponseModel();

        try {
            Optional<InternalQualityAssuranceEntity> existingEntityOptional = internalQualityAssuranceRepository.findById(id);

            if (existingEntityOptional.isPresent()) {
                InternalQualityAssuranceEntity existingEntity = existingEntityOptional.get();

                // Update the fields of the existing entity with the values from updatedEntity
                existingEntity.setTitle(updatedEntity.getTitle());
                existingEntity.setDescription(updatedEntity.getDescription());

                // Save the updated entity
                InternalQualityAssuranceEntity updatedEntityInDB = internalQualityAssuranceRepository.save(existingEntity);

                responseModel.setCode(200); // 200 OK
                responseModel.setMessage("Internal Quality Assurance updated successfully");
                responseModel.setData(updatedEntityInDB);
            } else {
                responseModel.setCode(404); // 404 Not Found
                responseModel.setMessage("Internal Quality Assurance not found");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // 500 Internal Server Error
            responseModel.setMessage("Failed to update Internal Quality Assurance");
        }

        return responseModel;
    }

    @Override
    public ResponseModel deleteInternalQualityAssurance(Long id) {
        ResponseModel responseModel = new ResponseModel();

        try {
            Optional<InternalQualityAssuranceEntity> entityToDeleteOptional = internalQualityAssuranceRepository.findById(id);

            if (entityToDeleteOptional.isPresent()) {
                // Delete the entity from the database
                internalQualityAssuranceRepository.deleteById(id);

                responseModel.setCode(200); // 200 OK
                responseModel.setMessage("Internal Quality Assurance deleted successfully");
            } else {
                responseModel.setCode(404); // 404 Not Found
                responseModel.setMessage("Internal Quality Assurance not found");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // 500 Internal Server Error
            responseModel.setMessage("Failed to delete Internal Quality Assurance");
        }

        return responseModel;
    }
}
