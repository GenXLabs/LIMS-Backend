package com.kiu.lims.service;

import com.kiu.lims.entity.BiohazardEntity;
import com.kiu.lims.model.ResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface BiohazardService {
    ResponseModel getAllBiohazard();


    ResponseModel createBiohazard(BiohazardEntity manual, MultipartFile file);

    ResponseModel updateBiohazard(Long manualId, Map<String, Object> updatedFields);

    ResponseModel deleteBiohazard(Long manualId, Map<String, Object> deletedFields);

    ResponseModel getAllBiohazardById(Long manualId);

    Resource downloadBiohazardPdf(Long manualId);
}
