package com.kiu.lims.controller;

import com.kiu.lims.entity.InstrumentEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.InstrumentService;
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
@RequestMapping("/api/v1/lims/instrument")
public class InstrumentController {
    @Autowired
    private InstrumentService instrumentService;

    private static final Logger logger = LoggerFactory.getLogger(InstrumentController.class);

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping("/get-all")
    public ResponseModel getAllInstrument() {
        return instrumentService.getAllInstrument();
    }

    @GetMapping("/get-by-id/{manualId}")
    public ResponseModel getAllInstrumentById(@PathVariable Long manualId) {
        return instrumentService.getAllInstrumentById(manualId);
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseModel createInstrument(
            @RequestPart("manual") InstrumentEntity manual,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        // Log the received request body for debugging
        logger.debug("Received request body: " + manual.toString());
        return instrumentService.createInstrument(manual, file);
        // Your implementation here
    }
    @PutMapping("/update/{manualId}")
    public ResponseModel updateInstrument(
            @PathVariable Long manualId,
            @RequestBody Map<String, Object> updatedFields
    ) {
        return instrumentService.updateInstrument(manualId, updatedFields);
    }
    @PutMapping("/delete/{manualId}")
    public ResponseModel deleteInstrument(
            @PathVariable Long manualId,
            @RequestBody Map<String, Object> deletedFields
    ) {
        return instrumentService.deleteInstrument(manualId, deletedFields);
    }

    @GetMapping("/download-pdf/{manualId}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable Long manualId) {
        Resource pdfResource = instrumentService.downloadInstrumentPdf(manualId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfResource.getFilename() + "\"")
                .body(pdfResource);
    }
}







