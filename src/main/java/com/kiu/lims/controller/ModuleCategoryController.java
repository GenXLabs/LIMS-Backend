package com.kiu.lims.controller;

import com.kiu.lims.entity.ModuleCategoryEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.ModuleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/lims/module-categories")
public class ModuleCategoryController {

    @Autowired
    private ModuleCategoryService moduleCategoryService;

    @GetMapping("/get-all")
    public ResponseModel getAllModuleCategories() {
        return moduleCategoryService.getAllModuleCategories();
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseModel getModuleCategoryById(@PathVariable Long id) {
        return moduleCategoryService.getModuleCategoryById(id);
    }

    @PostMapping("/add")
    public ResponseModel addModuleCategory(@RequestBody ModuleCategoryEntity moduleCategory) {
        return moduleCategoryService.addModuleCategory(moduleCategory);
    }

//    @PutMapping("/update")
//    public ResponseModel updateModuleCategory(@RequestBody ModuleCategoryEntity moduleCategory) {
//        return moduleCategoryService.updateModuleCategory(moduleCategory);
//    }

    @PutMapping("/update/{categoryId}")
    public ResponseModel updateModuleCategory(@PathVariable Long categoryId, @RequestBody Map<String, Object> updateData) {
        return moduleCategoryService.updateModuleCategory(categoryId, updateData);
    }

    @PutMapping("/delete/{categoryId}")
    public ResponseModel deleteModuleCategory(@PathVariable Long categoryId, @RequestBody Map<String, Object> deleteData) {
        return moduleCategoryService.deleteModuleCategory(categoryId, deleteData);
    }


}
