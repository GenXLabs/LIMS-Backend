package com.kiu.lims.service;

import com.kiu.lims.entity.PracticalManualEntity;
import com.kiu.lims.model.ResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PracticalManualService {
    ResponseModel getAllPracticalManuals();

    ResponseModel createPracticalManual(PracticalManualEntity manual, MultipartFile file);

    ResponseModel updatePracticalManual(Long manualId, Map<String, Object> updatedFields);

//    ResponseModel deletePracticalManual(Long manualId);

    ResponseModel deletePracticalManual(Long manualId, Map<String, Object> deletedFields);

    ResponseModel getPracticalManualById(Long manualId);

    Resource downloadManualPdf(Long manualId);

}