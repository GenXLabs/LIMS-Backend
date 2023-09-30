package com.kiu.lims.service;

import com.kiu.lims.entity.InstrumentEntity;
import com.kiu.lims.model.ResponseModel;

public interface InstrumentService {
    ResponseModel getAllSopManagement();
    ResponseModel createSop(InstrumentEntity newSop);
    ResponseModel updateSop(Long sopId, InstrumentEntity updatedSop);
    ResponseModel deleteSop(Long sopId);
}
