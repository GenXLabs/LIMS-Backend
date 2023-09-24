package com.kiu.lims.controller;

import com.kiu.lims.entity.LabReservationEntity;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.service.LabReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lims/lab-reservation")
public class LabReservationController {

    @Autowired
    private LabReservationService labReservationService;

    @GetMapping("/get-all")
    public ResponseModel getAllLabReservations() {
        return labReservationService.getAllLabReservations();
    }

    @GetMapping("/get-by-requester/{requesterId}")
    public ResponseModel getLabReservationsByRequester(@PathVariable Long requesterId) {
        return labReservationService.getLabReservationsByRequester(requesterId);
    }

    @PostMapping("/add/{userId}")
    public ResponseModel addLabReservation(@RequestBody LabReservationEntity reservation , @PathVariable Long userId) {
        return labReservationService.addLabReservation(userId, reservation);
    }

    @PutMapping("/update")
    public ResponseModel updateLabReservation(@RequestBody LabReservationEntity reservation) {
        return labReservationService.updateLabReservation(reservation);
    }

    @PutMapping("/update-status/{reservationId}/{status}")
    public ResponseModel updateReservationStatus(
            @PathVariable Long reservationId,
            @PathVariable Byte status) {
        return labReservationService.updateReservationStatus(reservationId, status);
    }

    @DeleteMapping("/delete/{reservationId}")
    public ResponseModel deleteLabReservation(@PathVariable Long reservationId) {
        return labReservationService.deleteLabReservation(reservationId);
    }
}
