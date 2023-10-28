package com.kiu.lims.controller;

import com.kiu.lims.entity.AuditEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/lims/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    private static final Logger logger = LoggerFactory.getLogger(AuditController.class);

    @Autowired
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/get-all")
    public ResponseModel getAllAudit() {
        return auditService.getAllAudit();
    }

    @GetMapping("/get-by-id/{manualId}")
    public ResponseModel getAuditById(@PathVariable Long manualId) {
        return auditService.getAuditById(manualId);
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseModel createAudit(
            @RequestPart("manual") AuditEntity manual,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        // Log the received request body for debugging
        logger.debug("Received request body: " + manual.toString());
        return auditService.createAudit(manual, file);
        // Your implementation here
    }

    @PutMapping("/update/{manualId}")
    public ResponseModel updateAudit(
            @PathVariable Long manualId,
            @RequestBody Map<String, Object> updatedFields
    ) {
        return auditService.updateAudit(manualId, updatedFields);
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
    public ResponseModel deleteAudit(
            @PathVariable Long manualId,
            @RequestBody Map<String, Object> deletedFields
    ) {
        return auditService.deleteAudit(manualId, deletedFields);
    }

    @GetMapping("/download-pdf/{manualId}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable Long manualId) {
        Resource pdfResource = auditService.downloadManualPdf(manualId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfResource.getFilename() + "\"")
                .body(pdfResource);
    }

}
