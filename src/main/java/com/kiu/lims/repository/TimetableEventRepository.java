package com.kiu.lims.repository;

import com.kiu.lims.entity.TimetableEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableEventRepository extends JpaRepository<TimetableEvent, Long> {

    // Custom query methods can be added here
}
