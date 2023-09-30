package com.kiu.lims.service;

import com.kiu.lims.entity.InternalQualityAssuranceEntity;
import com.kiu.lims.model.ResponseModel;

public interface InternalQualityAssuranceService {
    ResponseModel getAllInternalQualityAssurance();
    ResponseModel createInternalQualityAssurance(InternalQualityAssuranceEntity newEntity);
    ResponseModel updateInternalQualityAssurance(Long id, InternalQualityAssuranceEntity updatedEntity);
    ResponseModel deleteInternalQualityAssurance(Long id);
}

