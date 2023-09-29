package com.kiu.lims.controller;

import com.kiu.lims.entity.PracticalManualEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.PracticalManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/lims/practical-manual")
public class PracticalManualController {

    @Autowired
    private PracticalManualService practicalManualService;

    @GetMapping("/get-all")
    public ResponseModel getAllPracticalManuals() {
        return practicalManualService.getAllPracticalManuals();
    }

    @GetMapping("/get-by-id/{manualId}")
    public ResponseModel getPracticalManualById(@PathVariable Long manualId) {
        return practicalManualService.getPracticalManualById(manualId);
    }

    @PostMapping("/create")
    public ResponseModel createPracticalManual(@RequestBody PracticalManualEntity manual) {
        return practicalManualService.createPracticalManual(manual);
    }

    @PutMapping("/update/{manualId}")
    public ResponseModel updatePracticalManual(@PathVariable Long manualId, @RequestBody PracticalManualEntity updatedManual) {
        return practicalManualService.updatePracticalManual(manualId, updatedManual);
    }

    @DeleteMapping("/delete/{manualId}")
    public ResponseModel deletePracticalManual(@PathVariable Long manualId) {
        return practicalManualService.deletePracticalManual(manualId);
    }
}
