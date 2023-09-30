package com.kiu.lims.service;

import com.kiu.lims.entity.ModuleCategoryEntity;
import com.kiu.lims.model.ResponseModel;

import java.util.Map;

public interface ModuleCategoryService {
    ResponseModel getAllModuleCategories();

    ResponseModel getModuleCategoryById(Long id);

    ResponseModel addModuleCategory(ModuleCategoryEntity moduleCategory);

    ResponseModel updateModuleCategory(Long categoryId, Map<String, Object> updateData);

    ResponseModel  deleteModuleCategory(Long categoryId, Map<String, Object> deleteData);
}
