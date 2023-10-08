package com.kiu.lims.service;

import com.kiu.lims.entity.InternalQualityAssuranceEntity;
import com.kiu.lims.model.ResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface InternalQualityAssuranceService {
    ResponseModel getAllInternalQualityAssurance();

    Resource downloadManualPdf(Long manualId);

    ResponseModel createInternalQualityAssurance(InternalQualityAssuranceEntity manual, MultipartFile file);

    ResponseModel updateInternalQualityAssurance(Long manualId, Map<String, Object> updatedFields);

    ResponseModel deleteInternalQualityAssurance(Long manualId, Map<String, Object> deletedFields);

    ResponseModel getInternalQualityById(Long manualId);
}

