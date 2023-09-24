package com.kiu.lims.service;

import com.kiu.lims.entity.LabReservationEntity;
import com.kiu.lims.model.ResponseModel;

public interface LabReservationService {
    ResponseModel getAllLabReservations();

    ResponseModel getLabReservationsByRequester(Long requesterId);


    ResponseModel addLabReservation(Long userId, LabReservationEntity reservation);

    ResponseModel updateLabReservation(LabReservationEntity reservation);

    ResponseModel updateReservationStatus(Long reservationId, Byte status);

    ResponseModel deleteLabReservation(Long reservationId);
}
