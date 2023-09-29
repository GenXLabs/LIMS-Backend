package com.kiu.lims.controller;

import com.kiu.lims.entity.InstrumentEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lims/instrument")
public class InstrumentController {
    @Autowired
    private InstrumentService instrumentService;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModel> getAllSopManagement() {
        ResponseModel response = instrumentService.getAllSopManagement();
        return ResponseEntity.status(response.getCode()).body(response);

    }

    @PostMapping("/create")
    public ResponseEntity<ResponseModel> createSop(@RequestBody InstrumentEntity newSop) {
        ResponseModel response = instrumentService.createSop(newSop);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/update/{sopId}")
    public ResponseEntity<ResponseModel> updateSop(
            @PathVariable Long sopId,
            @RequestBody InstrumentEntity updatedSop) {
        ResponseModel response = instrumentService.updateSop(sopId, updatedSop);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("/delete/{sopId}")
    public ResponseEntity<ResponseModel> deleteSop(@PathVariable Long sopId) {
        ResponseModel response = instrumentService.deleteSop(sopId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/get-by-id/{sopId}")
    public ResponseEntity<ResponseModel> getSopById(@PathVariable Long sopId) {
        ResponseModel responseModel = new ResponseModel();
        return ResponseEntity.status(responseModel.getCode()).body(responseModel);
    }
}
