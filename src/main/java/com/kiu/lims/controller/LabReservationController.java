package com.kiu.lims.controller;

import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.LabReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lims/lab-reservation")
public class LabReservationController {

    @Autowired
    private LabReservationService labReservationService;

    @GetMapping("/get-all")
    public ResponseModel getAllLabReservations() {
        return labReservationService.getAllLabReservations();
    }


}
