package com.kiu.lims.service.impl;

import com.kiu.lims.entity.LabReservationEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.LabReservationRepository;
import com.kiu.lims.service.LabReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabReservationServiceImpl implements LabReservationService {

    @Autowired
    private LabReservationRepository labReservationRepository;

    @Override
    public ResponseModel getAllLabReservations() {
        ResponseModel responseModel = new ResponseModel();

        List<LabReservationEntity> labReservationEntityList = labReservationRepository.findAll();

        responseModel.setCode(200);

        responseModel.setMessage("Successful");

        responseModel.setData(labReservationEntityList);

        return responseModel;
    }
}
