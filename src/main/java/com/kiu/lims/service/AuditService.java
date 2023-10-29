package com.kiu.lims.service;

import com.kiu.lims.entity.AuditEntity;
import com.kiu.lims.model.ResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface AuditService {

    ResponseModel getAllAudit();

    Resource downloadManualPdf(Long manualId);

    ResponseModel createAudit(AuditEntity manual, MultipartFile file);

    ResponseModel updateAudit(Long manualId, Map<String, Object> updatedFields);

    ResponseModel deleteAudit(Long manualId, Map<String, Object> deletedFields);

    ResponseModel getAuditById(Long manualId);

}
