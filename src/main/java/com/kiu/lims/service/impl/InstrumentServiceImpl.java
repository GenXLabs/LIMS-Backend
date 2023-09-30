package com.kiu.lims.service.impl;

import com.kiu.lims.entity.InstrumentEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.InstrumentRepository;
import com.kiu.lims.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    private InstrumentRepository instrumentRepository;

    @Override
    public ResponseModel getAllSopManagement() {
        ResponseModel responseModel = new ResponseModel();
        List<InstrumentEntity> instrumentEntityList;

        try {
            instrumentEntityList = instrumentRepository.findAll();

            if (instrumentEntityList.isEmpty()) {
                responseModel.setCode(204);
                responseModel.setMessage("No SOPs found");
            } else {
                responseModel.setMessage("Success");
                responseModel.setCode(200);
                responseModel.setData(instrumentEntityList);
            }
        } catch (Exception e) {
            responseModel.setCode(500);
            responseModel.setMessage("Internal server error");
        }

        return responseModel;
    }

    @Override
    public ResponseModel createSop(InstrumentEntity newSop) {
        ResponseModel responseModel = new ResponseModel();

        try {
            // Validate and save the new SOP
            InstrumentEntity savedSop = instrumentRepository.save(newSop);

            responseModel.setCode(201);
            responseModel.setMessage("SOP created successfully");
            responseModel.setData(savedSop);
        } catch (Exception e) {
            responseModel.setCode(500);
            responseModel.setMessage("Internal server error");
        }

        return responseModel;
    }

    @Override
    public ResponseModel updateSop(Long sopId, InstrumentEntity updatedSop) {
        ResponseModel responseModel = new ResponseModel();

        try {
            Optional<InstrumentEntity> existingSopOptional = instrumentRepository.findById(sopId);

            if (existingSopOptional.isPresent()) {
                InstrumentEntity existingSop = existingSopOptional.get();

                // Update the SOP fields as needed
                existingSop.setTitle(updatedSop.getTitle());
                existingSop.setDescription(updatedSop.getDescription());
                // Update other fields as needed

                // Save the updated SOP
                InstrumentEntity updatedInstrumentEntity = instrumentRepository.save(existingSop);

                responseModel.setCode(200);
                responseModel.setMessage("SOP updated successfully");
                responseModel.setData(updatedInstrumentEntity);
            } else {
                responseModel.setCode(404);
                responseModel.setMessage("SOP not found");
            }
        } catch (Exception e) {
            responseModel.setCode(500);
            responseModel.setMessage("Internal server error");
        }

        return responseModel;
    }

    @Override
    public ResponseModel deleteSop(Long sopId) {
        ResponseModel responseModel = new ResponseModel();

        try {
            Optional<InstrumentEntity> sopOptional = instrumentRepository.findById(sopId);

            if (sopOptional.isPresent()) {
                instrumentRepository.delete(sopOptional.get());

                responseModel.setCode(200);
                responseModel.setMessage("SOP deleted successfully");
            } else {
                responseModel.setCode(404);
                responseModel.setMessage("SOP not found");
            }
        } catch (Exception e) {
            responseModel.setCode(500);
            responseModel.setMessage("Internal server error");
        }

        return responseModel;
    }
}
