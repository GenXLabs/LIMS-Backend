package com.kiu.lims.controller;

import com.kiu.lims.entity.BiohazardEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.BiohazardService;
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

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lims/biohazard")
public class BiohazardController {

    @Autowired
private BiohazardService biohazardService;

    private static final Logger logger = LoggerFactory.getLogger(BiohazardController.class);

    @Autowired
    public BiohazardController(BiohazardService biohazardService) {
        this.biohazardService = biohazardService;
    }

    @GetMapping("/get-all")
    public ResponseModel getAllBiohazard() {
        return biohazardService.getAllBiohazard();
    }

    @GetMapping("/get-by-id/{manualId}")
    public ResponseModel getAllBiohazardById(@PathVariable Long manualId) {
        return biohazardService.getAllBiohazardById(manualId);
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseModel createBiohazard(
            @RequestPart("manual") BiohazardEntity manual,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        // Log the received request body for debugging
        logger.debug("Received request body: " + manual.toString());
        return biohazardService.createBiohazard(manual, file);
        // Your implementation here
    }
    @PutMapping("/update/{manualId}")
    public ResponseModel updateBiohazard(
            @PathVariable Long manualId,
            @RequestBody Map<String, Object> updatedFields
    ) {
        return biohazardService.updateBiohazard(manualId, updatedFields);
    }
    @PutMapping("/delete/{manualId}")
    public ResponseModel deleteBiohazard(
            @PathVariable Long manualId,
            @RequestBody Map<String, Object> deletedFields
    ) {
        return biohazardService.deleteBiohazard(manualId, deletedFields);
    }

    @GetMapping("/download-pdf/{manualId}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable Long manualId) {
        Resource pdfResource = biohazardService.downloadBiohazardPdf(manualId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfResource.getFilename() + "\"")
                .body(pdfResource);
    }
}
