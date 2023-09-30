package com.kiu.lims.controller;

import com.kiu.lims.entity.InternalQualityAssuranceEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.InternalQualityAssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lims/internal-quality-assurance")
public class InternalQualityAssuranceController {

    @Autowired
    private InternalQualityAssuranceService internalQualityAssuranceService;

    @GetMapping("/get-all")
    public ResponseModel getAllInternalQualityAssurance() {
        return internalQualityAssuranceService.getAllInternalQualityAssurance();
    }

    @PostMapping("/create")
    public ResponseModel createInternalQualityAssurance(
            @RequestBody InternalQualityAssuranceEntity newEntity) {
        return internalQualityAssuranceService.createInternalQualityAssurance(newEntity);
    }

    @PutMapping("/update/{id}")
    public ResponseModel updateInternalQualityAssurance(
            @PathVariable Long id,
            @RequestBody InternalQualityAssuranceEntity updatedEntity) {
        return internalQualityAssuranceService.updateInternalQualityAssurance(id, updatedEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseModel deleteInternalQualityAssurance(
            @PathVariable Long id) {
        return internalQualityAssuranceService.deleteInternalQualityAssurance(id);
    }
}
