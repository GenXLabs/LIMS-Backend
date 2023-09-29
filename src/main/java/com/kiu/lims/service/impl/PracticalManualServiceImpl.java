package com.kiu.lims.service.impl;

import com.kiu.lims.entity.PracticalManualEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.PracticalManualRepository;
import com.kiu.lims.service.PracticalManualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PracticalManualServiceImpl implements PracticalManualService {

    private final PracticalManualRepository practicalManualRepository;

    @Override
    public ResponseModel getAllPracticalManuals() {
        ResponseModel responseModel = new ResponseModel();
        List<PracticalManualEntity> practicalManualEntityList = practicalManualRepository.findAll();

        if (practicalManualEntityList.isEmpty()) {
            responseModel.setCode(204); // No Content
            responseModel.setMessage("No practical manuals found.");
        } else {
            responseModel.setCode(200);
            responseModel.setMessage("Practical manuals retrieved successfully.");
            responseModel.setData(practicalManualEntityList);
        }
        return responseModel;
    }

    @Override
    public ResponseModel createPracticalManual(PracticalManualEntity manual) {
        ResponseModel responseModel = new ResponseModel();
        try {
            PracticalManualEntity savedManual = practicalManualRepository.save(manual);
            responseModel.setCode(201); // Created
            responseModel.setMessage("Practical manual created successfully.");
            responseModel.setData(savedManual);
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while creating the practical manual.");
            // You can log the exception or include additional error details in the message.
        }
        return responseModel;
    }

    @Override
    public ResponseModel updatePracticalManual(Long manualId, PracticalManualEntity updatedManual) {
        ResponseModel responseModel = new ResponseModel();
        try {
            Optional<PracticalManualEntity> optionalManual = practicalManualRepository.findById(manualId);
            if (optionalManual.isPresent()) {
                updatedManual.setManualId(manualId);
                PracticalManualEntity savedManual = practicalManualRepository.save(updatedManual);
                responseModel.setCode(200); // OK
                responseModel.setMessage("Practical manual updated successfully.");
                responseModel.setData(savedManual);
            } else {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage("Practical manual not found.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while updating the practical manual.");
            // You can log the exception or include additional error details in the message.
        }
        return responseModel;
    }

    @Override
    public ResponseModel deletePracticalManual(Long manualId) {
        ResponseModel responseModel = new ResponseModel();
        try {
            Optional<PracticalManualEntity> optionalManual = practicalManualRepository.findById(manualId);
            if (optionalManual.isPresent()) {
                practicalManualRepository.deleteById(manualId);
                responseModel.setCode(204); // No Content
                responseModel.setMessage("Practical manual deleted successfully.");
            } else {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage("Practical manual not found.");
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while deleting the practical manual.");
            // You can log the exception or include additional error details in the message.
        }
        return responseModel;
    }

    @Override
    public ResponseModel getPracticalManualById(Long manualId) {
        ResponseModel responseModel = new ResponseModel();
        Optional<PracticalManualEntity> optionalManual = practicalManualRepository.findById(manualId);

        if (optionalManual.isPresent()) {
            responseModel.setCode(200); // OK
            responseModel.setMessage("Practical manual retrieved successfully.");
            responseModel.setData(optionalManual.get());
        } else {
            responseModel.setCode(404); // Not Found
            responseModel.setMessage("Practical manual not found.");
        }
        return responseModel;
    }
}
