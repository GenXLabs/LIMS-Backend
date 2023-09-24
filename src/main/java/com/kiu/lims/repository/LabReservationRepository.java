package com.kiu.lims.repository;

import com.kiu.lims.entity.LabReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabReservationRepository extends JpaRepository<LabReservationEntity, Long> {
    List<LabReservationEntity> findByRequesterId(Long requesterId);

    List<LabReservationEntity> findByDateAndStartTimeAndEndTimeAndVenue(String date, String startTime, String endTime, String venue);
}
