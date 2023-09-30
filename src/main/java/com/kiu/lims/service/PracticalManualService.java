package com.kiu.lims.service;

import com.kiu.lims.entity.PracticalManualEntity;
import com.kiu.lims.model.ResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PracticalManualService {
    ResponseModel getAllPracticalManuals();

    ResponseModel createPracticalManual(PracticalManualEntity manual, MultipartFile file);

    ResponseModel updatePracticalManual(Long manualId, PracticalManualEntity updatedManual);

    ResponseModel deletePracticalManual(Long manualId);

    ResponseModel getPracticalManualById(Long manualId);

    Resource downloadManualPdf(Long manualId);

}