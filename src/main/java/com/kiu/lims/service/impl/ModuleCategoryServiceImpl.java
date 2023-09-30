package com.kiu.lims.service.impl;

import com.kiu.lims.entity.ModuleCategoryEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.ModuleCategoryRepository;
import com.kiu.lims.service.ModuleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ModuleCategoryServiceImpl implements ModuleCategoryService {

    @Autowired
    private ModuleCategoryRepository moduleCategoryRepository;

    @Override
    public ResponseModel getAllModuleCategories() {
        ResponseModel responseModel = new ResponseModel();
        List<ModuleCategoryEntity> moduleCategories = moduleCategoryRepository.findAll();

        if (!moduleCategories.isEmpty()) {
            responseModel.setCode(200); // OK
            responseModel.setMessage("Module categories retrieved successfully.");
            responseModel.setData(moduleCategories);
        } else {
            responseModel.setCode(204); // No Content
            responseModel.setMessage("No module categories found.");
        }

        return responseModel;
    }

    @Override
    public ResponseModel getModuleCategoryById(Long id) {
        ResponseModel responseModel = new ResponseModel();
        Optional<ModuleCategoryEntity> optionalModuleCategory = moduleCategoryRepository.findById(id);

        if (optionalModuleCategory.isPresent()) {
            responseModel.setCode(200); // OK
            responseModel.setMessage("Module category retrieved successfully.");
            responseModel.setData(optionalModuleCategory.get());
        } else {
            responseModel.setCode(404); // Not Found
            responseModel.setMessage("Module category not found.");
        }

        return responseModel;
    }

    @Override
    public ResponseModel addModuleCategory(ModuleCategoryEntity moduleCategory) {
        ResponseModel responseModel = new ResponseModel();

        try {

            // Set the "created_at" timestamp with the current timestamp
            moduleCategory.setCreatedAt(Timestamp.from(Instant.now()));

            ModuleCategoryEntity createdCategory = moduleCategoryRepository.save(moduleCategory);
            responseModel.setCode(201); // Created
            responseModel.setMessage("Module category created successfully.");
            responseModel.setData(createdCategory);
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while creating the module category.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }

    @Override
    public ResponseModel updateModuleCategory(Long categoryId, Map<String, Object> updateData) {
        ResponseModel responseModel = new ResponseModel();

        try {
            // Ensure that the categoryId parameter is used for finding the category in the repository
            if (categoryId != null) {
                Optional<ModuleCategoryEntity> optionalCategory = moduleCategoryRepository.findById(categoryId);
                if (optionalCategory.isPresent()) {
                    ModuleCategoryEntity existingCategory = optionalCategory.get();

                    // Update only the fields from the JSON object, if provided
                    if (updateData.containsKey("category_name")) {
                        existingCategory.setCategoryName((String) updateData.get("category_name"));
                    }
                    if (updateData.containsKey("updated_by")) {
                        existingCategory.setUpdatedBy((Integer) updateData.get("updated_by"));
                    }

                    // Update the "updated_at" field with the current timestamp
                    existingCategory.setUpdatedAt(Timestamp.from(Instant.now()));

                    // Add more fields to update as needed

                    ModuleCategoryEntity updatedCategory = moduleCategoryRepository.save(existingCategory);
                    responseModel.setCode(200); // OK
                    responseModel.setMessage("Module category updated successfully.");
                    responseModel.setData(updatedCategory);
                } else {
                    responseModel.setCode(404); // Not Found
                    responseModel.setMessage("Module category not found.");
                }
            } else {
                responseModel.setCode(400); // Bad Request
                responseModel.setMessage("Module category ID is required for updating.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while updating the module category.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }

    @Override
    public ResponseModel deleteModuleCategory(Long categoryId, Map<String, Object> deleteData) {
        ResponseModel responseModel = new ResponseModel();

        try {
            // Ensure that the categoryId parameter is used for finding the category in the repository
            if (categoryId != null) {
                Optional<ModuleCategoryEntity> optionalCategory = moduleCategoryRepository.findById(categoryId);
                if (optionalCategory.isPresent()) {
                    ModuleCategoryEntity existingCategory = optionalCategory.get();

                    if (deleteData.containsKey("deleted_by")) {
                        existingCategory.setDeletedBy((Integer) deleteData.get("deleted_by"));
                    }

                    existingCategory.setDeletedAt(Timestamp.from(Instant.now()));

                    ModuleCategoryEntity deletedCategory = moduleCategoryRepository.save(existingCategory);
                    responseModel.setCode(200); // OK
                    responseModel.setMessage("Module category deleted successfully.");
                    responseModel.setData(deletedCategory);
                } else {
                    responseModel.setCode(404); // Not Found
                    responseModel.setMessage("Module category not found.");
                }
            } else {
                responseModel.setCode(400); // Bad Request
                responseModel.setMessage("Module category ID is required for deleting.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while deleting the module category.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }

//    @Override
//    public ResponseModel deleteModuleCategory(Long id) {
//        ResponseModel responseModel = new ResponseModel();
//
//        try {
//            Optional<ModuleCategoryEntity> optionalCategory = moduleCategoryRepository.findById(id);
//            if (optionalCategory.isPresent()) {
//                moduleCategoryRepository.deleteById(id);
//                responseModel.setCode(204); // No Content
//                responseModel.setMessage("Module category deleted successfully.");
//            } else {
//                responseModel.setCode(404); // Not Found
//                responseModel.setMessage("Module category not found.");
//            }
//        } catch (Exception e) {
//            responseModel.setCode(500); // Internal Server Error
//            responseModel.setMessage("An error occurred while deleting the module category.");
//            // You can log the exception or include additional error details in the message.
//        }
//
//        return responseModel;
//    }
}
