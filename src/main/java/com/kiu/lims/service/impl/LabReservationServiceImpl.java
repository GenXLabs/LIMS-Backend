package com.kiu.lims.service.impl;

import com.kiu.lims.entity.LabReservationEntity;
import com.kiu.lims.entity.User;
import com.kiu.lims.repository.UserRepository;
import com.kiu.lims.model.ResponseModel;
import com.kiu.lims.repository.LabReservationRepository;
import com.kiu.lims.service.LabReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabReservationServiceImpl implements LabReservationService {

    private final LabReservationRepository labReservationRepository;
    private final UserRepository UserRepository;

    @Override
    public ResponseModel getAllLabReservations() {
        ResponseModel responseModel = new ResponseModel();
        List<LabReservationEntity> labReservationEntityList;

        try {
            labReservationEntityList = labReservationRepository.findAll();

            if (labReservationEntityList.isEmpty()) {
                responseModel.setCode(204); // No Content
                responseModel.setMessage("No lab reservations found.");
            } else {
                // Iterate through the reservations to fetch requester information
                for (LabReservationEntity reservation : labReservationEntityList) {
                    Long requesterId = reservation.getRequesterId();
                    User requester = UserRepository.findById(Math.toIntExact(requesterId)).orElse(null);

                    // If requester is found, set email and full_name
                    if (requester != null) {
                        reservation.setRequesterEmail(requester.getEmail());
                        reservation.setRequesterFullName(requester.getFullName());
                        reservation.setRequesterPhone(requester.getPhoneNumber());
                    }
                }

                responseModel.setCode(200);
                responseModel.setMessage("Lab reservations retrieved successfully.");
                responseModel.setData(labReservationEntityList);
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while retrieving lab reservations.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }


    @Override
    public ResponseModel getLabReservationsByRequester(Long requesterId) {
        ResponseModel responseModel = new ResponseModel();
        List<LabReservationEntity> labReservationEntityList;

        try {
            labReservationEntityList = labReservationRepository.findByRequesterId(requesterId);

            if (labReservationEntityList.isEmpty()) {
                responseModel.setCode(204); // No Content
                responseModel.setMessage("No lab reservations found for the requester.");
            } else {
                responseModel.setCode(200);
                responseModel.setMessage("Lab reservations retrieved successfully.");
                responseModel.setData(labReservationEntityList);
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while retrieving lab reservations.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }

    @Override
    public ResponseModel addLabReservation(Long userId, LabReservationEntity reservation) {

        ResponseModel responseModel = new ResponseModel();

        try {
            // Check if there is already a reservation for the same time, date, and venue
            List<LabReservationEntity> existingReservations = labReservationRepository.findByDateAndStartTimeAndEndTimeAndVenue(
                    reservation.getDate(),
                    reservation.getStartTime(),
                    reservation.getEndTime(),
                    reservation.getVenue()
            );

            if (!existingReservations.isEmpty()) {
                responseModel.setCode(409); // Conflict
                responseModel.setMessage("A reservation already exists for the specified time, date, and venue.");
            } else {
                // Set the user_id in the reservation entity as the created_by
                reservation.setCreatedBy(Math.toIntExact(userId));
                reservation.setRequesterId(userId);

                LabReservationEntity savedReservation = labReservationRepository.save(reservation);
                responseModel.setCode(201); // Created
                responseModel.setMessage("Lab reservation added successfully.");
                responseModel.setData(savedReservation);
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while adding the lab reservation.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }



    @Override
    public ResponseModel updateLabReservation(LabReservationEntity reservation) {
        ResponseModel responseModel = new ResponseModel();

        try {
            LabReservationEntity existingReservation = labReservationRepository.findById(reservation.getReservationId()).orElse(null);

            if (existingReservation == null) {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage("Lab reservation not found.");
            } else {
                // Update all fields of the existing reservation
                existingReservation.setTitle(reservation.getTitle());
                existingReservation.setBatch(reservation.getBatch());
                existingReservation.setVenue(reservation.getVenue());
                existingReservation.setDate(reservation.getDate());
                existingReservation.setStartTime(reservation.getStartTime());
                existingReservation.setEndTime(reservation.getEndTime());
                existingReservation.setDescription(reservation.getDescription());
                existingReservation.setCalendar(reservation.getCalendar());
                existingReservation.setRequesterId(reservation.getRequesterId());
                existingReservation.setStatus(reservation.getStatus());
                existingReservation.setCreatedBy(reservation.getCreatedBy());
                existingReservation.setCreatedAt(reservation.getCreatedAt());
                existingReservation.setUpdatedBy(reservation.getUpdatedBy());
                existingReservation.setUpdatedAt(reservation.getUpdatedAt());
                existingReservation.setDeletedBy(reservation.getDeletedBy());
                existingReservation.setDeletedAt(reservation.getDeletedAt());

                LabReservationEntity updatedReservation = labReservationRepository.save(existingReservation);
                responseModel.setCode(200); // OK
                responseModel.setMessage("Lab reservation updated successfully.");
                responseModel.setData(updatedReservation);
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while updating the lab reservation.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }

    @Override
    public ResponseModel updateReservationStatus(Long reservationId, Byte status) {
        ResponseModel responseModel = new ResponseModel();

        try {
            LabReservationEntity existingReservation = labReservationRepository.findById(reservationId).orElse(null);

            if (existingReservation == null) {
                responseModel.setCode(404); // Not Found
                responseModel.setMessage("Lab reservation not found.");
            } else {
                // Update the status of the existing reservation
                existingReservation.setStatus(status);

                LabReservationEntity updatedReservation = labReservationRepository.save(existingReservation);
                responseModel.setCode(200); // OK
                responseModel.setMessage("Lab reservation status updated successfully.");
                responseModel.setData(updatedReservation);
            }
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while updating the lab reservation status.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }

    @Override
    public ResponseModel deleteLabReservation(Long reservationId) {
        ResponseModel responseModel = new ResponseModel();

        try {
            labReservationRepository.deleteById(reservationId);
            responseModel.setCode(204); // No Content
            responseModel.setMessage("Lab reservation deleted successfully.");
        } catch (Exception e) {
            responseModel.setCode(500); // Internal Server Error
            responseModel.setMessage("An error occurred while deleting the lab reservation.");
            // You can log the exception or include additional error details in the message.
        }

        return responseModel;
    }
}
