package com.kiu.lims.service;

import com.kiu.lims.entity.TimetableEvent;
import org.springframework.data.crossstore.ChangeSetPersister;
import com.kiu.lims.service.impl.DuplicateTimetableEventException;

import java.util.List;

public interface TimetableEventService {
    List<TimetableEvent> getAllTimetableEvents() throws ChangeSetPersister.NotFoundException;
    TimetableEvent getTimetableEventById(Long id) throws ChangeSetPersister.NotFoundException;
    TimetableEvent createTimetableEvent(TimetableEvent event) throws DuplicateTimetableEventException;
    TimetableEvent updateTimetableEvent(Long id, TimetableEvent updatedEvent) throws ChangeSetPersister.NotFoundException, DuplicateTimetableEventException;
    void deleteTimetableEvent(Long id) throws ChangeSetPersister.NotFoundException;
}
