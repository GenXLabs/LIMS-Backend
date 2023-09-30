package com.kiu.lims.controller;

import com.kiu.lims.entity.ResearchEntity;
import com.kiu.lims.service.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lims/research-management")
public class ResearchController {

    @Autowired
    private ResearchService researchService;

    @GetMapping("/get-all")
    public ResponseEntity<List<ResearchEntity>> getAllResearch() {
        List<ResearchEntity> researchList = researchService.getAllResearch();
        return ResponseEntity.ok(researchList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResearchEntity> getResearchById(@PathVariable Long id) {
        ResearchEntity research = researchService.getResearchById(id);
        if (research != null) {
            return ResponseEntity.ok(research);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResearchEntity> createResearch(@RequestBody ResearchEntity research) {
        ResearchEntity createdResearch = researchService.createResearch(research);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResearch);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResearchEntity> updateResearchById(@PathVariable Long id, @RequestBody ResearchEntity updatedResearch) {
        ResearchEntity research = researchService.updateResearchById(id, updatedResearch);
        if (research != null) {
            return ResponseEntity.ok(research);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResearchById(@PathVariable Long id) {
        if (researchService.deleteResearchById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
