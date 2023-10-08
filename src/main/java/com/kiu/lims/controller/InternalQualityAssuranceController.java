package com.kiu.lims.controller;

import com.kiu.lims.entity.InternalQualityAssuranceEntity;
import com.kiu.lims.entity.PracticalManualEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.InternalQualityAssuranceService;
import com.kiu.lims.service.PracticalManualService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/lims/internal-quality-assurance")
public class InternalQualityAssuranceController {

    @Autowired
    private InternalQualityAssuranceService internalQualityAssuranceService;

    private static final Logger logger = LoggerFactory.getLogger(InternalQualityAssuranceController.class);

    @Autowired
    public InternalQualityAssuranceController(InternalQualityAssuranceService internalQualityAssuranceService) {
        this.internalQualityAssuranceService = internalQualityAssuranceService;
    }

    @GetMapping("/get-all")
    public ResponseModel getAllInternalQualityAssurance() {
        return internalQualityAssuranceService.getAllInternalQualityAssurance();
    }

    @GetMapping("/get-by-id/{manualId}")
    public ResponseModel getInternalQualityById(@PathVariable Long manualId) {
        return internalQualityAssuranceService.getInternalQualityById(manualId);
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseModel createInternalQualityAssurance(
            @RequestPart("manual") InternalQualityAssuranceEntity manual,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        // Log the received request body for debugging
        logger.debug("Received request body: " + manual.toString());
        return internalQualityAssuranceService.createInternalQualityAssurance(manual, file);
        // Your implementation here
    }

    @PutMapping("/update/{manualId}")
    public ResponseModel updateInternalQualityAssurance(
            @PathVariable Long manualId,
            @RequestBody Map<String, Object> updatedFields
    ) {
        return internalQualityAssuranceService.updateInternalQualityAssurance(manualId, updatedFields);
    }



//    @PutMapping("/update/{manualId}")
//    public ResponseModel updatePracticalManual(@PathVariable Long manualId, @RequestBody PracticalManualEntity updatedManual) {
//        return practicalManualService.updatePracticalManual(manualId, updatedManual);
//    }

//    @DeleteMapping("/delete/{manualId}")
//    public ResponseModel deletePracticalManual(@PathVariable Long manualId) {
//        return practicalManualService.deletePracticalManual(manualId);
//    }

    @PutMapping("/delete/{manualId}")
    public ResponseModel deleteInternalQualityAssurance(
            @PathVariable Long manualId,
            @RequestBody Map<String, Object> deletedFields
    ) {
        return internalQualityAssuranceService.deleteInternalQualityAssurance(manualId, deletedFields);
    }

    @GetMapping("/download-pdf/{manualId}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable Long manualId) {
        Resource pdfResource = internalQualityAssuranceService.downloadManualPdf(manualId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfResource.getFilename() + "\"")
                .body(pdfResource);
    }
}
