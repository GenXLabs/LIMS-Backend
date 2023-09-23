package com.kiu.lims.repository;

import com.kiu.lims.entity.LabReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabReservationRepository extends JpaRepository<LabReservationEntity, Long> {
}
