package com.kiu.lims.repository;

import com.kiu.lims.entity.TimetableEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface TimetableEventRepository extends JpaRepository<TimetableEvent, Long> {
    boolean existsByDateAndTimeAndVenue(String date, String time, String venue);
}

