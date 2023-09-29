package com.kiu.lims.service;

import com.kiu.lims.entity.PracticalManualEntity;
import com.kiu.lims.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface PracticalManualService {
    ResponseModel getAllPracticalManuals();

    ResponseModel createPracticalManual(PracticalManualEntity manual);

    ResponseModel updatePracticalManual(Long manualId, PracticalManualEntity updatedManual);

    ResponseModel deletePracticalManual(Long manualId);

    ResponseModel getPracticalManualById(Long manualId);

}