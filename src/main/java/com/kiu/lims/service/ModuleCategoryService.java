package com.kiu.lims.service;

import com.kiu.lims.entity.ModuleCategoryEntity;
import com.kiu.lims.model.ResponseModel;

import java.util.List;

public interface ModuleCategoryService {
    ResponseModel getAllModuleCategories();

    ResponseModel getModuleCategoryById(Long id);

    ResponseModel addModuleCategory(ModuleCategoryEntity moduleCategory);

    ResponseModel updateModuleCategory(ModuleCategoryEntity moduleCategory);

    ResponseModel deleteModuleCategory(Long id);
}
