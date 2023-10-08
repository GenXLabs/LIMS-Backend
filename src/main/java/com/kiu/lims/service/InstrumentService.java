package com.kiu.lims.service;

import com.kiu.lims.entity.InstrumentEntity;
import com.kiu.lims.entity.PracticalManualEntity;
import com.kiu.lims.model.ResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface InstrumentService {


    ResponseModel getAllInstrument();

    ResponseModel createInstrument(InstrumentEntity manual, MultipartFile file);


    ResponseModel  updateInstrument(Long manualId, Map<String, Object> updatedFields);



    ResponseModel deleteInstrument(Long manualId, Map<String, Object> deletedFields);

    ResponseModel getAllInstrumentById(Long manualId);

    Resource downloadInstrumentPdf(Long manualId);

}