package com.kiu.lims.controller;

import com.kiu.lims.entity.PracticalManualEntity;
import com.kiu.lims.model.ResponseModel;
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

@CrossOrigin
@RestController
@RequestMapping("/api/v1/lims/practical-manual")
public class PracticalManualController {

    @Autowired
    private PracticalManualService practicalManualService;

    private static final Logger logger = LoggerFactory.getLogger(PracticalManualController.class);

    @Autowired
    public PracticalManualController(PracticalManualService practicalManualService) {
        this.practicalManualService = practicalManualService;
    }

    @GetMapping("/get-all")
    public ResponseModel getAllPracticalManuals() {
        return practicalManualService.getAllPracticalManuals();
    }

    @GetMapping("/get-by-id/{manualId}")
    public ResponseModel getPracticalManualById(@PathVariable Long manualId) {
        return practicalManualService.getPracticalManualById(manualId);
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseModel createPracticalManual(
            @RequestPart("manual") PracticalManualEntity manual,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        // Log the received request body for debugging
        logger.debug("Received request body: " + manual.toString());
        return practicalManualService.createPracticalManual(manual, file);
        // Your implementation here
    }


    @PutMapping("/update/{manualId}")
    public ResponseModel updatePracticalManual(@PathVariable Long manualId, @RequestBody PracticalManualEntity updatedManual) {
        return practicalManualService.updatePracticalManual(manualId, updatedManual);
    }

    @DeleteMapping("/delete/{manualId}")
    public ResponseModel deletePracticalManual(@PathVariable Long manualId) {
        return practicalManualService.deletePracticalManual(manualId);
    }

    @GetMapping("/download-pdf/{manualId}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable Long manualId) {
        Resource pdfResource = practicalManualService.downloadManualPdf(manualId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfResource.getFilename() + "\"")
                .body(pdfResource);
    }
}
