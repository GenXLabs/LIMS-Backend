package com.kiu.lims.service;

import com.kiu.lims.entity.TimetableEvent;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TimetableEventService {

    List<TimetableEvent> getAllTimetableEvents();

    TimetableEvent getTimetableEventById(Long id) throws ChangeSetPersister.NotFoundException;

    TimetableEvent createTimetableEvent(TimetableEvent event);

    TimetableEvent updateTimetableEvent(Long id, TimetableEvent event) throws ChangeSetPersister.NotFoundException;

    void deleteTimetableEvent(Long id);
}
