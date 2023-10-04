package com.kiu.lims.service;

import com.kiu.lims.entity.InstrumentEntity;
import com.kiu.lims.model.ResponseModel;
import org.springframework.core.io.Resource;

public interface InstrumentService {
    ResponseModel getAllSopManagement();
    ResponseModel createSop(InstrumentEntity newSop);
    ResponseModel updateSop(Long sopId, InstrumentEntity updatedSop);
    ResponseModel deleteSop(Long sopId);


}
